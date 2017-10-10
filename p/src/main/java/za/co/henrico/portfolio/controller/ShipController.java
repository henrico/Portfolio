package za.co.henrico.portfolio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.model.Ship;
import za.co.henrico.portfolio.service.RestService;

@RestController
@RequestMapping("/rest/ship")
@CrossOrigin(origins = "*")
public class ShipController extends AbstractAppController<Ship> {

	protected RestService getService() {
		return shipService;
	}

}
