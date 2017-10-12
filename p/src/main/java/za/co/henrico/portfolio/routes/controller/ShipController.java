package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/ship")
@CrossOrigin(origins = "*")
public class ShipController extends AbstractAppController<Ship> {

	protected RestService getService() {
		return shipService;
	}

}
