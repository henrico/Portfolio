package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Order;

public interface OrderService extends RestService<Order> {

	Collection<Order> findUnfilledOrders();

}
