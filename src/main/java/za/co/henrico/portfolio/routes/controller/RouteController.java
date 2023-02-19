package za.co.henrico.portfolio.routes.controller;

import org.modelmapper.PropertyMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.dto.RouteDTO;
import za.co.henrico.portfolio.routes.model.Route;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/route")

public class RouteController extends AbstractAppController<Route, Long, RouteDTO> {

	protected RestService<Route, Long> getService() {
		return routeService;
	}

	@Override
	protected Class<Route> getEntityClass(RouteDTO route) {
		return Route.class;
	}

	@Override
	protected Class<RouteDTO> getDtoClass(Route route) {
		return RouteDTO.class;
	}

	@Override
	protected Route mapChildren(Route entity, RouteDTO dto) {
		entity.setDestinationA(portService.getById(dto.getDestinationA().getId()).orElseThrow());
		entity.setDestinationB(portService.getById(dto.getDestinationB().getId()).orElseThrow());
		return entity;
	}

	@Override
	protected void applyMappingRules() {
		modelMapper.addMappings(new PropertyMap<RouteDTO, Route>() {
			@Override
			protected void configure() {
				skip(destination.getDestinationA());
				skip(destination.getDestinationB());
			}
		});
	}

}
