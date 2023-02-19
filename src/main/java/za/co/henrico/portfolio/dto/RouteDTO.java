package za.co.henrico.portfolio.dto;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class RouteDTO {
	
	private Long id;

	private PortSimpleDTO destinationA;

	private PortSimpleDTO destinationB;

	private Long distance;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PortSimpleDTO getDestinationA() {
		return destinationA;
	}

	public void setDestinationA(PortSimpleDTO destinationA) {
		this.destinationA = destinationA;
	}

	public PortSimpleDTO getDestinationB() {
		return destinationB;
	}

	public void setDestinationB(PortSimpleDTO destinationB) {
		this.destinationB = destinationB;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

}
