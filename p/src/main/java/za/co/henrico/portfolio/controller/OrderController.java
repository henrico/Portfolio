package za.co.henrico.portfolio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.service.RestService;

@RestController
@RequestMapping("/rest/order")
@CrossOrigin(origins = "*")
public class OrderController extends AbstractAppController<Order> {

	protected RestService getService() {
		return orderService;
	}

}
