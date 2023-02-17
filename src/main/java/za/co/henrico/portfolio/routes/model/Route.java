package za.co.henrico.portfolio.routes.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;

@Entity
@NamedQuery(name = "Route.findRouteFromPorts", query = "select r from Route r where (r.destinationA = ?1 and r.destinationB = ?2) or (r.destinationA = ?2 and r.destinationB = ?1) order by r.distance desc")
public class Route extends AbstractPersistable<Long> {

	@OneToOne
	@JoinColumn(name = "destination_a")
	private Port destinationA;

	@OneToOne
	@JoinColumn(name = "destination_b")
	private Port destinationB;

	@Basic
	private Long distance;

	public Port getDestinationA() {
		return destinationA;
	}

	public void setDestinationA(Port destinationA) {
		this.destinationA = destinationA;
	}

	public Port getDestinationB() {
		return destinationB;
	}

	public void setDestinationB(Port destinationB) {
		this.destinationB = destinationB;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

}
