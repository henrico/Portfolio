package za.co.henrico.portfolio.colors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.colors.model.Scheme;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {

}
