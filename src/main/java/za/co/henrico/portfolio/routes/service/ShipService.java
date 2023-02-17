package za.co.henrico.portfolio.routes.service;

import java.util.Collection;
import java.util.Date;

import za.co.henrico.portfolio.routes.model.Ship;

public interface ShipService extends RestService<Ship, Long> {

	Collection<Ship> getAvailibeShipsForOrder(Long order, Long source, Date collectionDate);

}
