package za.co.henrico.portfolio.model;

import javax.persistence.Basic;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Product extends AbstractPersistable<Long> {

	@Basic
	private String name;
}
