package za.co.henrico.portfolio.model;

import javax.persistence.Basic;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Ship extends AbstractPersistable<Long> {

	@Basic
	public String name;

	@Basic
	public Integer speed;

	@Basic
	public Integer capacity;
}
