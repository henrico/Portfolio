package za.co.henrico.portfolio.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Route extends AbstractPersistable<Long> {

	@OneToOne
	@JoinColumn(name = "destination_a")
	private Port destinationA;

	@OneToOne
	@JoinColumn(name = "destination_b")
	private Port destinationB;

	@Basic
	private Long distance;

}
