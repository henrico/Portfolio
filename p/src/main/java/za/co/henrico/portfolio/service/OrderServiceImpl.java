package za.co.henrico.portfolio.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.repository.OrderRepository;
import za.co.henrico.portfolio.repository.ScheduleRepository;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public Collection<Order> getOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Collection<Order> deleteOrder(long id) {
		orderRepository.delete(id);
		return getOrders();
	}

	@Override
	@Transactional
	public Collection<Order> saveOrder(Order order) {
		
		if (order.getId()!=null)scheduleRepository.delete(scheduleRepository.findByOrder(order));
		
		orderRepository.save(order);
		return getOrders();
	}

	@Override
	public Collection<Order> findUnfilledOrders() {
		Collection<Order> list = orderRepository.findUnfilledOrders();
		
		return list;
	}

	

}
