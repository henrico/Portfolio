package za.co.henrico.portfolio.routes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.routes.model.Route;
import za.co.henrico.portfolio.routes.repository.RouteRepository;

@Component
public class RouteServiceImpl extends AbstractRestServiceImpl<Route, Long> implements RouteService {

	@Autowired
	private RouteRepository routeRepository;

	protected JpaRepository<Route, Long> getRepository() {
		return routeRepository;
	}

}
