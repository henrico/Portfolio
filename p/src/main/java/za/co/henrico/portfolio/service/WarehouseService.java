package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Warehouse;

public interface WarehouseService {

	Collection<Warehouse> getWarehouses();

	Collection<Warehouse> deleteWarehouse(long id);

	Collection<Warehouse> saveWarehouse(Warehouse warehouse);

}
