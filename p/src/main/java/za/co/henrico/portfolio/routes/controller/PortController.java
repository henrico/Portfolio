package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/port")
@CrossOrigin(origins = "*")
public class PortController extends AbstractAppController<Port> {

	protected RestService getService() {
		return portService;
	}

}
