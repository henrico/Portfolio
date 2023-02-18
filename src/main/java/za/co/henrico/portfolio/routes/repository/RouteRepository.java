package za.co.henrico.portfolio.routes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

	Optional<Route> findRouteFromPorts(Port portA, Port portB);

}
