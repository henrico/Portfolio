package za.co.henrico.portfolio.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.co.henrico.portfolio.model.Product;
import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.model.Ship;
import za.co.henrico.portfolio.model.Warehouse;
import za.co.henrico.portfolio.service.ProductService;
import za.co.henrico.portfolio.service.RouteService;
import za.co.henrico.portfolio.service.ShipService;
import za.co.henrico.portfolio.service.WarehouseService;

@Controller
@CrossOrigin(origins = "*")
public class AppController {
	
	@Autowired
	ShipService shipService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	WarehouseService warehouseService;

	@GetMapping("/")
	@ResponseBody
	public String helloWorld() {
		return "Hello World";
	}
	
	@RequestMapping("/ships")
	public @ResponseBody Collection<Ship> getShips() {
		return shipService.getShips();
	}

	@RequestMapping(value="/ship/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Collection<Ship> deleteShips(@PathVariable("id") long id) {
		return shipService.deleteShip(id);
	}
	
	@RequestMapping(value="/ship/{id}", method=RequestMethod.PUT)
	public @ResponseBody Collection<Ship> saveShip(@PathVariable("id") long id, @RequestBody Ship ship) {
		return shipService.saveShip(ship);
	}
	
	@RequestMapping(value="/ship", method=RequestMethod.POST)
	public @ResponseBody Collection<Ship> addShips(@RequestBody Ship ship) {
		return shipService.saveShip(ship);
	}
	
	@RequestMapping("/routes")
	public @ResponseBody Collection<Route> getRoutes() {
		return routeService.getRoutes();
	}

	@RequestMapping(value="/route/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Collection<Route> deleteRoutes(@PathVariable("id") long id) {
		return routeService.deleteRoute(id);
	}
	
	@RequestMapping(value="/route/{id}", method=RequestMethod.PUT)
	public @ResponseBody Collection<Route> saveRoute(@PathVariable("id") long id, @RequestBody Route route) {
		return routeService.saveRoute(route);
	}
	
	@RequestMapping(value="/route", method=RequestMethod.POST)
	public @ResponseBody Collection<Route> addRoutes(@RequestBody Route route) {
		return routeService.saveRoute(route);
	}
	
	@RequestMapping("/products")
	public @ResponseBody Collection<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value="/product/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Collection<Product> deleteProducts(@PathVariable("id") long id) {
		return productService.deleteProduct(id);
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.PUT)
	public @ResponseBody Collection<Product> saveProduct(@PathVariable("id") long id, @RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public @ResponseBody Collection<Product> addProducts(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@RequestMapping("/warehouses")
	public @ResponseBody Collection<Warehouse> getWarehouses() {
		return warehouseService.getWarehouses();
	}

	@RequestMapping(value="/warehouse/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Collection<Warehouse> deleteWarehouses(@PathVariable("id") long id) {
		return warehouseService.deleteWarehouse(id);
	}
	
	@RequestMapping(value="/warehouse/{id}", method=RequestMethod.PUT)
	public @ResponseBody Collection<Warehouse> saveWarehouse(@PathVariable("id") long id, @RequestBody Warehouse warehouse) {
		return warehouseService.saveWarehouse(warehouse);
	}
	
	@RequestMapping(value="/warehouse", method=RequestMethod.POST)
	public @ResponseBody Collection<Warehouse> addWarehouses(@RequestBody Warehouse warehouse) {
		return warehouseService.saveWarehouse(warehouse);
	}
	
}
