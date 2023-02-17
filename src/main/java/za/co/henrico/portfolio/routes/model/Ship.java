package za.co.henrico.portfolio.routes.model;

import java.util.Collection;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Ship extends AbstractPersistable<Long> {

	@Basic
	private String name;

	@Basic
	private Integer speed;

	@Basic
	private Integer capacity;

	@OneToMany(mappedBy = "ship", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<Schedule> schedules;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}
