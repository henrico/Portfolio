package za.co.henrico.portfolio.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

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
