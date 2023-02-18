package za.co.henrico.portfolio.routes.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Route;
import za.co.henrico.portfolio.routes.model.Schedule;
import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.repository.OrderRepository;
import za.co.henrico.portfolio.routes.repository.PortRepository;
import za.co.henrico.portfolio.routes.repository.RouteRepository;
import za.co.henrico.portfolio.routes.repository.ScheduleRepository;
import za.co.henrico.portfolio.routes.repository.ShipRepository;
import za.co.henrico.portfolio.routes.utils.Utils;

@Component
public class ShipServiceImpl extends AbstractRestServiceImpl<Ship, Long> implements ShipService {

	@Autowired
	private ShipRepository shipRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private PortRepository portRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private Utils utils;

	@Transactional
	@Override
	public Collection<Ship> save(Ship ship) {
		if (ship.getId() != null)
			scheduleRepository.deleteAll(scheduleRepository.findByShip(ship));

		return super.save(ship);
	}

	protected JpaRepository<Ship, Long> getRepository() {
		return shipRepository;
	}
	
	@Override
	public Collection<Ship> getAvailibeShipsForOrder(Long orderId, Long sourceId, Date collectionDate) {
	    Port port = portRepository.findById(sourceId).orElse(null);
	    Order order = orderRepository.findById(orderId).orElse(null);

	    if (port == null || order == null) {
	        return Collections.emptyList();
	    }

	    List<Ship> ships = shipRepository.findAll();

	    ships.removeIf(ship -> {
	        Route route;
	        if (port.getId().equals(order.getDestination().getId())) {
	            route = new Route();
	            route.setDistance(0L);
	        } else {
	            route = routeRepository.findRouteFromPorts(port, order.getDestination()).orElseThrow();
	        }

	        Date endDate = utils.getEndDateFromDays(collectionDate, ship, route);
	        Collection<Schedule> schedules = scheduleRepository.findDateReleventSchedule(endDate, collectionDate, ship);

	        if (!schedules.isEmpty() || endDate.compareTo(order.getDeliveryDate()) > 0) {
	            return true;
	        }

	        Schedule lastSchedule = scheduleRepository.findLastScheduleRelatedToShip(ship).orElse(null);
	        if (lastSchedule != null) {
	            if (port.getId().equals(lastSchedule.getOrder().getDestination().getId())) {
	                route = new Route();
	                route.setDistance(0L);
	            } else {
	                route = routeRepository.findRouteFromPorts(lastSchedule.getOrder().getDestination(), port).orElseThrow();
	            }
	            Date arrivalDate = utils.getEndDateFromDays(lastSchedule.getDeliveryDate(), ship, route);
	            if (arrivalDate.compareTo(collectionDate) > 0) {
	                return true;
	            }

	            Schedule firstSchedule = scheduleRepository.findFirstScheduleRelatedToShipFromDate(ship, endDate).orElse(null);
	            if (firstSchedule != null) {
	                if (firstSchedule.getSource().getId().equals(order.getDestination().getId())) {
	                    route = new Route();
	                    route.setDistance(0L);
	                } else {
	                    route = routeRepository.findRouteFromPorts(firstSchedule.getSource(), order.getDestination()).orElseThrow();
	                }
	                arrivalDate = utils.getEndDateFromDays(endDate, ship, route);
	                if (arrivalDate.compareTo(firstSchedule.getCollectionDate()) > 0) {
	                    return true;
	                }
	            }
	        }

	        return false;
	    });

	    return ships;
	}

}
