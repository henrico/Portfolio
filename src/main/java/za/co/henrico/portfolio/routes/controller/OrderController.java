package za.co.henrico.portfolio.routes.controller;

import org.modelmapper.PropertyMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.dto.OrderDTO;
import za.co.henrico.portfolio.dto.PortDTO;
import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/order")

public class OrderController extends AbstractAppController<Order, Long, OrderDTO> {

	protected RestService<Order, Long> getService() {
		return orderService;
	}

	@Override
	protected Class<Order> getEntityClass(OrderDTO order) {
		return Order.class;
	}

	@Override
	protected Class<OrderDTO> getDtoClass(Order order) {
		return OrderDTO.class;
	}

	@Override
	protected Order mapChildren(Order entity, OrderDTO dto) {
		entity.setDestination(portService.getById(dto.getDestination().getId()).orElseThrow());
		entity.setProduct(productService.getById(dto.getProduct().getId()).orElseThrow());
		return entity;
	}
	
	@Override
	protected void applyMappingRules() {
		modelMapper.addMappings(new PropertyMap<OrderDTO, Order>() {
            @Override
            protected void configure() {
                skip(destination.getDestination());
                skip(destination.getProduct());
            }
        });
	}

}
