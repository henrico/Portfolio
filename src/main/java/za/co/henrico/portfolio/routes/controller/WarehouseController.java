package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Warehouse;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/warehouse")

public class WarehouseController extends AbstractAppController<Warehouse, Long> {

	protected RestService<Warehouse, Long> getService() {
		return warehouseService;
	}

}
