package za.co.henrico.portfolio.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Collection<Order> findUnfilledOrders();

	Collection<Order> findByOrderStatus(OrderStatus placed);

}
