package za.co.henrico.portfolio.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.repository.OrderRepository;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

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
	public Collection<Order> saveOrder(Order order) {
		orderRepository.save(order);
		return getOrders();
	}

}
