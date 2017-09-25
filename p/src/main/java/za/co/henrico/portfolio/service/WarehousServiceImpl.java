package za.co.henrico.portfolio.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Warehouse;
import za.co.henrico.portfolio.repository.WarehouseRepository;

@Component
public class WarehousServiceImpl implements WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Override
	public Collection<Warehouse> getWarehouses() {
		return warehouseRepository.findAll();
	}

	@Override
	public Collection<Warehouse> deleteWarehouse(long id) {
		warehouseRepository.delete(id);
		return getWarehouses();
	}

	@Override
	@Transactional
	public Collection<Warehouse> saveWarehouse(Warehouse warehouse) {
		if (warehouse.getId() != null)
			warehouseRepository.delete(warehouse.getId());
		warehouseRepository.save(warehouse);
		return getWarehouses();
	}

}
