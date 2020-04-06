package za.co.henrico.portfolio.routes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

	List<Warehouse> findByPort(Port port);

}
