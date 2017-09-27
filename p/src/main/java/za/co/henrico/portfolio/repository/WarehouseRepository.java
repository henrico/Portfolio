package za.co.henrico.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
	
	List<Warehouse> findByPort(Port port);
	
}
