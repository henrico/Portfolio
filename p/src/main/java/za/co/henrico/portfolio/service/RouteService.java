package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Route;

public interface RouteService {

	Collection<Route> getRoutes();

	Collection<Route> deleteRoute(long id);

	Collection<Route> saveRoute(Route route);

}
