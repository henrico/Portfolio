package za.co.henrico.portfolio.routes.controller;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.co.henrico.portfolio.routes.service.OrderService;
import za.co.henrico.portfolio.routes.service.PortService;
import za.co.henrico.portfolio.routes.service.ProductService;
import za.co.henrico.portfolio.routes.service.RestService;
import za.co.henrico.portfolio.routes.service.RouteService;
import za.co.henrico.portfolio.routes.service.ScheduleService;
import za.co.henrico.portfolio.routes.service.ShipService;
import za.co.henrico.portfolio.routes.service.WarehouseService;

public abstract class AbstractAppController<E extends AbstractPersistable> {

	@Autowired
	protected ShipService shipService;

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected PortService portService;

	@Autowired
	protected RouteService routeService;

	@Autowired
	protected ProductService productService;

	@Autowired
	protected WarehouseService warehouseService;

	@Autowired
	protected ScheduleService scheduleService;

	@RequestMapping("")
	@Transactional
	public @ResponseBody Collection<E> getList() {
		return getService().getList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody Collection<E> delete(@PathVariable("id") long id) {
		return getService().delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Collection<E> saveShip(@PathVariable("id") long id, @RequestBody E object) {
		return getService().save(object);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Collection<E> addShips(@RequestBody E object) {
		return getService().save(object);
	}

	protected abstract RestService getService();

}
