package za.co.henrico.portfolio.routes.service;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Route;
import za.co.henrico.portfolio.routes.model.Schedule;
import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.model.Warehouse;
import za.co.henrico.portfolio.routes.repository.OrderRepository;
import za.co.henrico.portfolio.routes.repository.PortRepository;
import za.co.henrico.portfolio.routes.repository.RouteRepository;
import za.co.henrico.portfolio.routes.repository.ScheduleRepository;
import za.co.henrico.portfolio.routes.repository.ShipRepository;
import za.co.henrico.portfolio.routes.repository.WarehouseRepository;
import za.co.henrico.portfolio.routes.utils.Utils;

@Component
public class WarehousServiceImpl extends AbstractRestServiceImpl<Warehouse, Long> implements WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;

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
	@Transactional
	public Collection<Warehouse> save(Warehouse warehouse) {
		if (warehouse.getId() != null)
			warehouseRepository.delete(warehouse);
		
		warehouseRepository.save(warehouse);

		return getList();
	}

	@Override
	public Collection<Warehouse> getAvailibeWarehouses(long orderId, long portId, long shipId, Date collectionDate) {
	    Optional<Ship> ship = shipRepository.findById(shipId);
	    Optional<Port> port = portRepository.findById(portId);
	    Optional<Order> order = orderRepository.findById(orderId);

	    if (ship.isEmpty() || port.isEmpty() || order.isEmpty()) {
	        return new LinkedList<>();
	    }

	    Route route = routeRepository.findRouteFromPorts(port.get(), order.get().getDestination()).orElse(null);
	    
	    if (route == null) {
	    	return new LinkedList<>();
	    }

	    Date endDate = utils.getEndDateFromDays(collectionDate, ship.get(), route);

	    List<Warehouse> allWarehouses = warehouseRepository.findByPort(order.get().getDestination());

	    List<Warehouse> availableWarehouses = allWarehouses.stream()
	            .filter(current -> {
	                Collection<Schedule> schedules = scheduleRepository.findDateReleventScheduleByWarehouse(order.get().getDeliveryDate(), endDate, current);

	                int totalStored = schedules.stream().mapToInt(Schedule::getStoredCrates).sum();

	                schedules = scheduleRepository.findByOrder(order.get());
	                int totalOrder = schedules.stream().mapToInt(Schedule::getStoredCrates).sum();

	                int needToStore = Math.min(order.get().getQuantity() - totalOrder, ship.get().getCapacity());

	                return current.getCapacity() - totalStored >= needToStore;
	            })
	            .collect(Collectors.toList());

	    return availableWarehouses;
	}

	protected JpaRepository<Warehouse, Long> getRepository() {
		return warehouseRepository;
	}

}
