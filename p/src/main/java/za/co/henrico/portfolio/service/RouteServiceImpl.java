package za.co.henrico.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.repository.RouteRepository;

@Component
public class RouteServiceImpl extends AbstractRestServiceImpl<Route> implements RouteService {

	@Autowired
	private RouteRepository routeRepository;

	protected JpaRepository<Route, Long> getRepository() {
		return routeRepository;
	}

}
