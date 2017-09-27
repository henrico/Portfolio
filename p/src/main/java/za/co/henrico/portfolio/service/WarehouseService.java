package za.co.henrico.portfolio.service;

import java.util.Collection;
import java.util.Date;

import za.co.henrico.portfolio.model.Warehouse;

public interface WarehouseService {

	Collection<Warehouse> getWarehouses();

	Collection<Warehouse> deleteWarehouse(long id);

	void saveWarehouse(Warehouse warehouse);

	Collection<Warehouse> getAvailibeWarehouses(long orderId, long portId, long shipId, Date parse);

}
