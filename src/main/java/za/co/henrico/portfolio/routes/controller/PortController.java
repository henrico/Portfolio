package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/port")

public class PortController extends AbstractAppController<Port, Long> {

	protected RestService<Port, Long> getService() {
		return portService;
	}

}
