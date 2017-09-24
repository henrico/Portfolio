package za.co.henrico.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import za.co.henrico.portfolio.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

}
