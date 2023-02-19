package za.co.henrico.portfolio.routes.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.PropertyMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import za.co.henrico.portfolio.dto.OrderSimpleDTO;
import za.co.henrico.portfolio.dto.PortSimpleDTO;
import za.co.henrico.portfolio.dto.ScheduleDTO;
import za.co.henrico.portfolio.dto.ShipSimpleDTO;
import za.co.henrico.portfolio.dto.WarehouseSimpleDTO;
import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Schedule;
import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.model.Warehouse;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/schedule")

public class ScheduleController extends AbstractAppController<Schedule, Long, ScheduleDTO> {

	protected RestService<Schedule, Long> getService() {
		return scheduleService;
	}

	@RequestMapping("/unfilledOrders")
	@Transactional
	public @ResponseBody Collection<OrderSimpleDTO> getUnfilledOrders() {
		return orderService.findUnfilledOrders().stream()
				.map(this::convertToOrdersDto)
				.collect(Collectors.toList());
	}

	@RequestMapping("/portsProducingProduct/{productId}")
	@Transactional
	public @ResponseBody Collection<PortSimpleDTO> getPortsProducingProduct(@PathVariable("productId") long id) {
		return portService.getPortsProducingProduct(id).stream()
				.map(this::convertToPortsDto)
				.collect(Collectors.toList());
	}

	@RequestMapping("/shipsForOrder/{orderId}/{portId}/{date}")
	@Transactional
	public @ResponseBody Collection<ShipSimpleDTO> getShipsForOrder(@PathVariable("orderId") long orderId,
			@PathVariable("portId") long portId, @PathVariable("date") String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return shipService.getAvailibeShipsForOrder(orderId, portId, dateFormat.parse(date)).stream()
				.map(this::convertToShipsDto)
				.collect(Collectors.toList());
	}

	@RequestMapping("/warehousesForOrder/{orderId}/{portId}/{shipId}/{date}")
	@Transactional
	public @ResponseBody Collection<WarehouseSimpleDTO> getShipsForOrder(@PathVariable("orderId") long orderId,
			@PathVariable("portId") long portId, @PathVariable("shipId") long shipId, @PathVariable("date") String date)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return warehouseService.getAvailibeWarehouses(orderId, portId, shipId, dateFormat.parse(date)).stream()
				.map(this::convertToWarehousesDto)
				.collect(Collectors.toList());
	}

	@Override
	protected Class<Schedule> getEntityClass(ScheduleDTO schedule) {
		return Schedule.class;
	}

	@Override
	protected Class<ScheduleDTO> getDtoClass(Schedule schedule) {
		return ScheduleDTO.class;
	}

	@Override
	protected Schedule mapChildren(Schedule entity, ScheduleDTO dto) {
		entity.setOrder(orderService.getById(dto.getOrder().getId()).orElseThrow());
		entity.setShip(shipService.getById(dto.getShip().getId()).orElseThrow());
		entity.setSource(portService.getById(dto.getSource().getId()).orElseThrow());
		entity.setWarehouse(warehouseService.getById(dto.getWarehouse().getId()).orElseThrow());
		return entity;
	}

	@Override
	protected void applyMappingRules() {
		modelMapper.addMappings(new PropertyMap<ScheduleDTO, Schedule>() {
            @Override
            protected void configure() {
                skip(destination.getWarehouse());
                skip(destination.getSource());
                skip(destination.getShip());
                skip(destination.getOrder());
            }
        });
	}
	
	protected OrderSimpleDTO convertToOrdersDto(Order entity) {
        return modelMapper.map(entity, OrderSimpleDTO.class);
    }
	
	protected PortSimpleDTO convertToPortsDto(Port entity) {
        return modelMapper.map(entity, PortSimpleDTO.class);
    }
	
	protected ShipSimpleDTO convertToShipsDto(Ship entity) {
        return modelMapper.map(entity, ShipSimpleDTO.class);
    }
	
	protected WarehouseSimpleDTO convertToWarehousesDto(Warehouse entity) {
        return modelMapper.map(entity, WarehouseSimpleDTO.class);
    }

}
