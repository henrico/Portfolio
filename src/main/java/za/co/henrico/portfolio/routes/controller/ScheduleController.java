package za.co.henrico.portfolio.routes.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Schedule;
import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.model.Warehouse;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/schedule")

public class ScheduleController extends AbstractAppController<Schedule, Long> {

	protected RestService<Schedule, Long> getService() {
		return scheduleService;
	}

	@RequestMapping("/unfilledOrders")
	@Transactional
	public @ResponseBody Collection<Order> getUnfilledOrders() {
		return orderService.findUnfilledOrders();
	}

	@RequestMapping("/portsProducingProduct/{productId}")
	@Transactional
	public @ResponseBody Collection<Port> getPortsProducingProduct(@PathVariable("productId") long id) {
		return portService.getPortsProducingProduct(id);
	}

	@RequestMapping("/shipsForOrder/{orderId}/{portId}/{date}")
	@Transactional
	public @ResponseBody Collection<Ship> getShipsForOrder(@PathVariable("orderId") long orderId,
			@PathVariable("portId") long portId, @PathVariable("date") String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return shipService.getAvailibeShipsForOrder(orderId, portId, dateFormat.parse(date));
	}

	@RequestMapping("/warehousesForOrder/{orderId}/{portId}/{shipId}/{date}")
	@Transactional
	public @ResponseBody Collection<Warehouse> getShipsForOrder(@PathVariable("orderId") long orderId,
			@PathVariable("portId") long portId, @PathVariable("shipId") long shipId, @PathVariable("date") String date)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return warehouseService.getAvailibeWarehouses(orderId, portId, shipId, dateFormat.parse(date));
	}

}
