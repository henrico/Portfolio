package za.co.henrico.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import za.co.henrico.portfolio.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
