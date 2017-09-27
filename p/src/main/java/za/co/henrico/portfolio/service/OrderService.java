package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.model.Port;

public interface OrderService {

	Collection<Order> getOrders();

	Collection<Order> deleteOrder(long id);

	Collection<Order> saveOrder(Order order);

	Collection<Order> findUnfilledOrders();

}
