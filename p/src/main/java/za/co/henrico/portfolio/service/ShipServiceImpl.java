package za.co.henrico.portfolio.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Ship;
import za.co.henrico.portfolio.repository.ShipRepository;

@Component
public class ShipServiceImpl implements ShipService {
	
	@Autowired
	private ShipRepository shipRepository;

	@Override
	public Collection<Ship> getShips() {
		return shipRepository.findAll();
	}

	@Override
	public Collection<Ship> deleteShip(long id) {
		shipRepository.delete(id);
		return getShips();
	}

	@Override
	public Collection<Ship> saveShip(Ship ship) {
		shipRepository.save(ship);
		return getShips();
	}

}
