package za.co.henrico.portfolio.routes.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.repository.OrderRepository;
import za.co.henrico.portfolio.routes.repository.ScheduleRepository;

@Component
public class OrderServiceImpl extends AbstractRestServiceImpl<Order> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	@Transactional
	public Collection<Order> save(Order order) {

		if (order.getId() != null)
			scheduleRepository.deleteAll(scheduleRepository.findByOrder(order));

		return super.save(order);
	}

	protected JpaRepository<Order, Long> getRepository() {
		return orderRepository;
	}

	@Override
	public Collection<Order> findUnfilledOrders() {
		Collection<Order> list = orderRepository.findUnfilledOrders();

		return list;
	}

}
