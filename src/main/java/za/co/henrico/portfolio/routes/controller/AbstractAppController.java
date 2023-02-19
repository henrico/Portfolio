package za.co.henrico.portfolio.routes.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import za.co.henrico.portfolio.dto.WarehouseDTO;
import za.co.henrico.portfolio.routes.service.OrderService;
import za.co.henrico.portfolio.routes.service.PortService;
import za.co.henrico.portfolio.routes.service.ProductService;
import za.co.henrico.portfolio.routes.service.RestService;
import za.co.henrico.portfolio.routes.service.RouteService;
import za.co.henrico.portfolio.routes.service.ScheduleService;
import za.co.henrico.portfolio.routes.service.ShipService;
import za.co.henrico.portfolio.routes.service.WarehouseService;

@RestController
@CrossOrigin(origins = "*")
public abstract class AbstractAppController<E extends AbstractPersistable<T>, T extends Serializable, D> {

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
	
	protected ModelMapper modelMapper = new ModelMapper();
	
	public AbstractAppController(){
		applyMappingRules();
	}

	@RequestMapping("")
	@Transactional
	public @ResponseBody List<D> getList() {
		return getService().getList().stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	@CrossOrigin(origins = "*")
	public @ResponseBody List<D> delete(@PathVariable("id") T id) {
		return getService().delete(id).stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@Transactional
	@CrossOrigin(origins = "*")
	public @ResponseBody List<D> saveShip(@PathVariable("id") T id, @RequestBody D dto) {
		E entity = convertToEntity(dto, id);
		return getService().save(entity).stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@Transactional
	@CrossOrigin(origins = "*")
	public @ResponseBody List<D> addShips(@RequestBody D dto) {
		E entity = convertToEntity(dto);
		return getService().save(entity).stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	protected abstract RestService<E, T> getService();
	
    protected D convertToDto(E entity) {
        return modelMapper.map(entity, getDtoClass(entity));
    }
    
    protected E convertToEntity(D dto) {
        return mapChildren(modelMapper.map(dto, getEntityClass(dto)), dto);
    }
    
    protected E convertToEntity(D dto, T id) {
    	
    	E ret = getService().getById(id).orElseThrow();
    	
        modelMapper.map(dto, ret);
        
        return mapChildren(ret, dto);
    }
    
    protected abstract Class<? extends E> getEntityClass(D dto);

    protected abstract Class<? extends D> getDtoClass(E entity);
    
    protected abstract E mapChildren(E entity, D dto);
    
    protected abstract void applyMappingRules();

}
