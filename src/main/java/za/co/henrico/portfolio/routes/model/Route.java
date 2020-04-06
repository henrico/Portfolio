package za.co.henrico.portfolio.routes.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

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
