package za.co.henrico.portfolio.routes.service;

import java.util.Collection;

import za.co.henrico.portfolio.routes.model.Order;

public interface OrderService extends RestService<Order> {

	Collection<Order> findUnfilledOrders();

}
