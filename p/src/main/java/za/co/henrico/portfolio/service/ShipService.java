package za.co.henrico.portfolio.service;

import java.util.Collection;
import java.util.Date;

import za.co.henrico.portfolio.model.Ship;

public interface ShipService extends RestService<Ship> {

	Collection<Ship> getAvailibeShipsForOrder(Long order, Long source, Date collectionDate);

}
