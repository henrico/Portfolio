package za.co.henrico.portfolio.routes.controller;

import org.modelmapper.PropertyMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.dto.ShipDTO;
import za.co.henrico.portfolio.dto.WarehouseDTO;
import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.model.Warehouse;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/ship")

public class ShipController extends AbstractAppController<Ship, Long, ShipDTO> {

	protected RestService<Ship, Long> getService() {
		return shipService;
	}

	@Override
	protected Class<Ship> getEntityClass(ShipDTO ship) {
		return Ship.class;
	}

	@Override
	protected Class<ShipDTO> getDtoClass(Ship ship) {
		return ShipDTO.class;
	}

	@Override
	protected Ship mapChildren(Ship entity, ShipDTO dto) {

		return entity;
	}
	
	@Override
	protected void applyMappingRules() {
	}

}
