package za.co.henrico.portfolio.routes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.routes.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
