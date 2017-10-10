package za.co.henrico.portfolio.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.model.Port;

public interface PortRepository extends JpaRepository<Port, Long> {

	Collection<Port> findByProductId(Long p);

}
