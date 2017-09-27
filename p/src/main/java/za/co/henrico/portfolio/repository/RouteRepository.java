package za.co.henrico.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
	
	Route findRouteFromPorts(Port portA,Port portB);

}
