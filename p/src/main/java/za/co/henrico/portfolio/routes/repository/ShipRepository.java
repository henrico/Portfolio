package za.co.henrico.portfolio.routes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.routes.model.Ship;

public interface ShipRepository extends JpaRepository<Ship, Long> {

}
