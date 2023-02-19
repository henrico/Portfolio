package za.co.henrico.portfolio.routes.controller;

import java.util.LinkedList;

import org.modelmapper.PropertyMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.dto.PortDTO;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Product;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/port")

public class PortController extends AbstractAppController<Port, Long, PortDTO> {

	protected RestService<Port, Long> getService() {
		return portService;
	}

	@Override
	protected Class<Port> getEntityClass(PortDTO portDTO) {
		return Port.class;
	}

	@Override
	protected Class<PortDTO> getDtoClass(Port port) {
		return PortDTO.class;
	}

	@Override
	protected Port mapChildren(Port entity, PortDTO dto) {
		entity.setProducts(new LinkedList<Product>());
		entity.getProducts().clear();
		dto.getProducts().stream().map(cur -> productService.getById(cur.getId()).orElseThrow())
				.forEach(entity.getProducts()::add);
		return entity;
	}

	@Override
	protected void applyMappingRules() {
		modelMapper.addMappings(new PropertyMap<PortDTO, Port>() {
			@Override
			protected void configure() {
				skip(destination.getProducts());
			}
		});
	}

}
