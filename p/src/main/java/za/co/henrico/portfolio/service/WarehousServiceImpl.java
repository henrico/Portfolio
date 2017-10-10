package za.co.henrico.portfolio.service;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.model.Schedule;
import za.co.henrico.portfolio.model.Ship;
import za.co.henrico.portfolio.model.Warehouse;
import za.co.henrico.portfolio.repository.OrderRepository;
import za.co.henrico.portfolio.repository.PortRepository;
import za.co.henrico.portfolio.repository.RouteRepository;
import za.co.henrico.portfolio.repository.ScheduleRepository;
import za.co.henrico.portfolio.repository.ShipRepository;
import za.co.henrico.portfolio.repository.WarehouseRepository;
import za.co.henrico.portfolio.utils.Utils;

@Component
public class WarehousServiceImpl extends AbstractRestServiceImpl<Warehouse> implements WarehouseService {

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
			warehouseRepository.delete(warehouse.getId());
		warehouseRepository.save(warehouse);

		return getList();
	}

	@Override
	public Collection<Warehouse> getAvailibeWarehouses(long orderId, long portId, long shipId, Date collectionDate) {

		Ship ship = shipRepository.findOne(shipId);
		Port port = portRepository.findOne(portId);
		Order order = orderRepository.findOne(orderId);

		if (ship == null || port == null || order == null) {
			return new LinkedList<Warehouse>();
		}

		Route route = routeRepository.findRouteFromPorts(port, order.getDestination());

		Date endDate = utils.getEndDateFromDays(collectionDate, ship, route);

		List<Warehouse> allWarehouses = warehouseRepository.findByPort(order.getDestination());

		Iterator<Warehouse> i = allWarehouses.iterator();
		while (i.hasNext()) {
			Warehouse current = i.next();

			Collection<Schedule> schedules = scheduleRepository
					.findDateReleventScheduleByWarehouse(order.getDeliveryDate(), endDate, current);

			int totalStored = 0;

			for (Schedule schedule : schedules) {
				totalStored += schedule.getStoredCrates();
			}

			schedules = scheduleRepository.findByOrder(order);

			int totalOrder = 0;
			for (Schedule schedule : schedules) {
				totalOrder += schedule.getStoredCrates();
			}

			int needToStore = order.getQuantity() - totalOrder < ship.getCapacity() ? order.getQuantity() - totalOrder
					: ship.getCapacity();

			if (current.getCapacity() - totalStored < needToStore) {
				i.remove();
			}

		}

		return allWarehouses;
	}

	protected JpaRepository<Warehouse, Long> getRepository() {
		return warehouseRepository;
	}

}
