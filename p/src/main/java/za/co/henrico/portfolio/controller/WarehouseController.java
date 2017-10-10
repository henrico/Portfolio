package za.co.henrico.portfolio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.model.Warehouse;
import za.co.henrico.portfolio.service.RestService;

@RestController
@RequestMapping("/rest/warehouse")
@CrossOrigin(origins = "*")
public class WarehouseController extends AbstractAppController<Warehouse> {

	protected RestService getService() {
		return warehouseService;
	}

}
