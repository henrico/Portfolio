package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Order;

public interface OrderService {

	Collection<Order> getOrders();

	Collection<Order> deleteOrder(long id);

	Collection<Order> saveOrder(Order order);

}
