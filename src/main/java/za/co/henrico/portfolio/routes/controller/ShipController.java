package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/ship")

public class ShipController extends AbstractAppController<Ship, Long> {

	protected RestService<Ship, Long> getService() {
		return shipService;
	}

}
