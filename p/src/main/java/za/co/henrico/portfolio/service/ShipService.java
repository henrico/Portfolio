package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Ship;

public interface ShipService {
	Collection<Ship> getShips();

	Collection<Ship> deleteShip(long id);

	Collection<Ship> saveShip(Ship ship);
}
