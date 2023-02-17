package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/order")

public class OrderController extends AbstractAppController<Order, Long> {

	protected RestService<Order, Long> getService() {
		return orderService;
	}

}
