package za.co.henrico.portfolio.routes.service;

import java.util.Collection;
import java.util.Date;

import za.co.henrico.portfolio.routes.model.Warehouse;

public interface WarehouseService extends RestService<Warehouse> {

	Collection<Warehouse> getAvailibeWarehouses(long orderId, long portId, long shipId, Date parse);

}
