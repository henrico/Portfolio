package za.co.henrico.portfolio.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.repository.RouteRepository;

@Component
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository routeRepository;

	@Override
	public Collection<Route> getRoutes() {
		return routeRepository.findAll();
	}

	@Override
	public Collection<Route> deleteRoute(long id) {
		routeRepository.delete(id);
		return getRoutes();
	}

	@Override
	public Collection<Route> saveRoute(Route route) {
		routeRepository.save(route);
		return getRoutes();
	}

}
