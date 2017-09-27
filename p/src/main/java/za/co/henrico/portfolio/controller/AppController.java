package za.co.henrico.portfolio.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.co.henrico.portfolio.model.ExternalWarehouse;
import za.co.henrico.portfolio.model.InternalWarehouse;
import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Product;
import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.model.Schedule;
import za.co.henrico.portfolio.model.Ship;
import za.co.henrico.portfolio.model.Warehouse;
import za.co.henrico.portfolio.service.OrderService;
import za.co.henrico.portfolio.service.PortService;
import za.co.henrico.portfolio.service.ProductService;
import za.co.henrico.portfolio.service.RouteService;
import za.co.henrico.portfolio.service.ScheduleService;
import za.co.henrico.portfolio.service.ShipService;
import za.co.henrico.portfolio.service.WarehouseService;

@Controller
@CrossOrigin(origins = "*")
public class AppController {

	@Autowired
	ShipService shipService;

	@Autowired
	OrderService orderService;

	@Autowired
	PortService portService;

	@Autowired
	RouteService routeService;

	@Autowired
	ProductService productService;

	@Autowired
	WarehouseService warehouseService;

	@Autowired
	ScheduleService scheduleService;

	@RequestMapping("/ships")
	@Transactional
	public @ResponseBody Collection<Ship> getShips() {
		return shipService.getShips();
	}

	@RequestMapping(value = "/ship/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<Ship> deleteShips(@PathVariable("id") long id) {
		return shipService.deleteShip(id);
	}

	@RequestMapping(value = "/ship/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Collection<Ship> saveShip(@PathVariable("id") long id, @RequestBody Ship ship) {
		return shipService.saveShip(ship);
	}

	@RequestMapping(value = "/ship", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Collection<Ship> addShips(@RequestBody Ship ship) {
		return shipService.saveShip(ship);
	}

	@RequestMapping("/schedules")
	@Transactional
	public @ResponseBody Collection<Schedule> getSchedules() {
		return scheduleService.getSchedules();
	}

	@RequestMapping(value = "/schedule/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<Schedule> deleteSchedules(@PathVariable("id") long id) {
		return scheduleService.deleteSchedule(id);
	}

	@RequestMapping(value = "/schedule/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Collection<Schedule> saveSchedule(@PathVariable("id") long id,
			@RequestBody Schedule schedule) {
		return scheduleService.saveSchedule(schedule);
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Collection<Schedule> addSchedules(@RequestBody Schedule schedule) {
		return scheduleService.saveSchedule(schedule);
	}

	@RequestMapping("/orders")
	@Transactional
	public @ResponseBody Collection<Order> getOrders() {
		return orderService.getOrders();
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<Order> deleteOrders(@PathVariable("id") long id) {
		return orderService.deleteOrder(id);
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Collection<Order> saveOrder(@PathVariable("id") long id, @RequestBody Order order) {
		return orderService.saveOrder(order);
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Collection<Order> addOrders(@RequestBody Order order) {
		return orderService.saveOrder(order);
	}

	@RequestMapping("/ports")
	@Transactional
	public @ResponseBody Collection<Port> getPorts() {
		return portService.getPorts();
	}

	@RequestMapping(value = "/port/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<Port> deletePorts(@PathVariable("id") long id) {
		return portService.deletePort(id);
	}

	@RequestMapping(value = "/port/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Collection<Port> savePort(@PathVariable("id") long id, @RequestBody Port port) {
		return portService.savePort(port);
	}

	@RequestMapping(value = "/port", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Collection<Port> addPorts(@RequestBody Port port) {
		return portService.savePort(port);
	}

	@RequestMapping("/routes")
	@Transactional
	public @ResponseBody Collection<Route> getRoutes() {
		return routeService.getRoutes();
	}

	@RequestMapping(value = "/route/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<Route> deleteRoutes(@PathVariable("id") long id) {
		return routeService.deleteRoute(id);
	}

	@RequestMapping(value = "/route/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Collection<Route> saveRoute(@PathVariable("id") long id, @RequestBody Route route) {
		return routeService.saveRoute(route);
	}

	@RequestMapping(value = "/route", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Collection<Route> addRoutes(@RequestBody Route route) {
		return routeService.saveRoute(route);
	}

	@RequestMapping("/products")
	@Transactional
	public @ResponseBody Collection<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<Product> deleteProducts(@PathVariable("id") long id) {
		return productService.deleteProduct(id);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Collection<Product> saveProduct(@PathVariable("id") long id, @RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Collection<Product> addProducts(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@RequestMapping("/warehouses")
	@Transactional
	public @ResponseBody Collection<Warehouse> getWarehouses() {
		return warehouseService.getWarehouses();
	}

	@RequestMapping(value = "/warehouse/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<Warehouse> deleteWarehouses(@PathVariable("id") long id) {
		return warehouseService.deleteWarehouse(id);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/warehouse/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody ResponseEntity saveWarehouse(@PathVariable("id") long id, @RequestBody Warehouse warehouse) {
		warehouseService.saveWarehouse(warehouse);
		return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/warehouse", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody ResponseEntity addWarehouse(@RequestBody Warehouse warehouse) {
		warehouseService.saveWarehouse(warehouse);
		return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
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
	public @ResponseBody Collection<Ship> getShipsForOrder(@PathVariable("orderId") long orderId,@PathVariable("portId") long portId,@PathVariable("date") String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return shipService.getAvailibeShipsForOrder(orderId, portId, dateFormat.parse(date));
	}
	
	@RequestMapping("/warehousesForOrder/{orderId}/{portId}/{shipId}/{date}")
	@Transactional
	public @ResponseBody Collection<Warehouse> getShipsForOrder(@PathVariable("orderId") long orderId,@PathVariable("portId") long portId,@PathVariable("shipId") long shipId,@PathVariable("date") String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return warehouseService.getAvailibeWarehouses(orderId, portId, shipId, dateFormat.parse(date));
	}

}
