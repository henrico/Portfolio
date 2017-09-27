package za.co.henrico.portfolio.service;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.model.Schedule;
import za.co.henrico.portfolio.model.Ship;
import za.co.henrico.portfolio.repository.OrderRepository;
import za.co.henrico.portfolio.repository.PortRepository;
import za.co.henrico.portfolio.repository.RouteRepository;
import za.co.henrico.portfolio.repository.ScheduleRepository;
import za.co.henrico.portfolio.repository.ShipRepository;
import za.co.henrico.portfolio.utils.Utils;

@Component
public class ShipServiceImpl implements ShipService {

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

	@Override
	public Collection<Ship> getShips() {
		return shipRepository.findAll();
	}

	@Override
	public Collection<Ship> deleteShip(long id) {
		shipRepository.delete(id);
		return getShips();
	}

	@Override
	@Transactional
	public Collection<Ship> saveShip(Ship ship) {
		
		if (ship.getId()!=null)scheduleRepository.delete(scheduleRepository.findByShip(ship));;
		
		shipRepository.save(ship);
		return getShips();
	}

	@Override
	public Collection<Ship> getAvailibeShipsForOrder(Long orderId, Long sourceId, Date collectionDate) {
		
		Port port = portRepository.findOne(sourceId);
		Order order = orderRepository.findOne(orderId);
		
		if (port == null || order == null) {
			return new LinkedList<Ship>();
		}
		
		List<Ship> ships = shipRepository.findAll();
		Iterator<Ship> i = ships.iterator();
		while (i.hasNext()) {
			
			Ship current = i.next();
			
			Route route;
			if (port.getId().equals(order.getDestination().getId())) {
				route = new Route();
				route.setDistance(0L);
			} else {
				route = routeRepository.findRouteFromPorts(port, order.getDestination());
			}
					
			
			Date endDate = utils.getEndDateFromDays(collectionDate, current, route);
			
			Collection<Schedule> schedules = scheduleRepository.findDateReleventSchedule(endDate,collectionDate,current);
			
			if (!schedules.isEmpty()) {
				i.remove();
			} else {
				
				if (endDate.compareTo(order.getDeliveryDate())>0) {
					i.remove();
				} else {
					
					Schedule lastSchedule = scheduleRepository.findLastScheduleRelatedToShip(current);
					if (lastSchedule!=null) {
						
						if (port.getId().equals(lastSchedule.getOrder().getDestination().getId())) {
							route = new Route();
							route.setDistance(0L);
						} else {
							route = routeRepository.findRouteFromPorts(lastSchedule.getOrder().getDestination(), port);
						}
						
						Date arrivalDate = utils.getEndDateFromDays(lastSchedule.getDeliveryDate(), current, route);
						
						if (arrivalDate.compareTo(collectionDate)>0) {
							i.remove();
						} else {
							
							Schedule firstSchedule = scheduleRepository.findFirstScheduleRelatedToShipFromDate(current,endDate);
							if (firstSchedule!=null) {
								
								if (firstSchedule.getSource().getId().equals(order.getDestination().getId())) {
									route = new Route();
									route.setDistance(0L);
								} else {
									route = routeRepository.findRouteFromPorts(firstSchedule.getSource(), order.getDestination());
								}
								
								arrivalDate = utils.getEndDateFromDays(endDate, current, route);
								
								if (arrivalDate.compareTo(firstSchedule.getCollectionDate())>0) {
									i.remove();
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return ships;
		
	}

}
