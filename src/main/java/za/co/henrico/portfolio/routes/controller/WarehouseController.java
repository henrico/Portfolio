package za.co.henrico.portfolio.routes.controller;

import org.modelmapper.PropertyMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.dto.ScheduleDTO;
import za.co.henrico.portfolio.dto.WarehouseDTO;
import za.co.henrico.portfolio.routes.model.ExternalWarehouse;
import za.co.henrico.portfolio.routes.model.InternalWarehouse;
import za.co.henrico.portfolio.routes.model.Schedule;
import za.co.henrico.portfolio.routes.model.Warehouse;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/warehouse")

public class WarehouseController extends AbstractAppController<Warehouse, Long, WarehouseDTO> {

	protected RestService<Warehouse, Long> getService() {
		return warehouseService;
	}

	@Override
	protected Class<? extends Warehouse> getEntityClass(WarehouseDTO warehouseDTO) {
		if (warehouseDTO.getType().equals("EXTERNAL")) return ExternalWarehouse.class;
		return InternalWarehouse.class;
	}

	@Override
	protected Class<? extends WarehouseDTO> getDtoClass(Warehouse warehouseDTO) {
		return WarehouseDTO.class;
	}

	@Override
	protected Warehouse mapChildren(Warehouse entity, WarehouseDTO dto) {
		entity.setPort(portService.getById(dto.getPort().getId()).orElseThrow());
		return entity;
	}
	
	protected Warehouse convertToEntity(WarehouseDTO dto, Long id) {
    	
		Warehouse ret = null;
		if (dto.getType().equals("EXTERNAL")) {
			ret = new ExternalWarehouse(id);
		} else {
			ret = new InternalWarehouse(id);
		}
    	
        modelMapper.map(dto, ret);
        
        return mapChildren(ret, dto);
    }
	
	@Override
	protected void applyMappingRules() {
		modelMapper.addMappings(new PropertyMap<WarehouseDTO, Warehouse>() {
            @Override
            protected void configure() {
                skip(destination.getPort());
            }
        });
	}

}
