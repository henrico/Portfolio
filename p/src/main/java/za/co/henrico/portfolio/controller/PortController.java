package za.co.henrico.portfolio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.service.RestService;

@RestController
@RequestMapping("/rest/port")
@CrossOrigin(origins = "*")
public class PortController extends AbstractAppController<Port> {

	protected RestService getService() {
		return portService;
	}

}
