package za.co.henrico.portfolio.routes.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PortProductsPK implements Serializable {

	@Basic
	@Column(name = "port_id")
	private Long portId;

	@Basic
	@Column(name = "product_id")
	private Long productId;

}
