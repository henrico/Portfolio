package za.co.henrico.portfolio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.service.RestService;

@RestController
@RequestMapping("/rest/route")
@CrossOrigin(origins = "*")
public class RouteController extends AbstractAppController<Route> {

	protected RestService getService() {
		return routeService;
	}

}
