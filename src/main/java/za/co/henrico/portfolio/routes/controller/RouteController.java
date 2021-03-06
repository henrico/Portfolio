package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Route;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/route")
@CrossOrigin(origins = "*")
public class RouteController extends AbstractAppController<Route> {

	protected RestService getService() {
		return routeService;
	}

}
