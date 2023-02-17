package za.co.henrico.portfolio.routes.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity(name = "port_products")
public class PortProducts {

	@EmbeddedId
	private PortProductsPK id;

	@MapsId(value = "product_id")
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	@MapsId(value = "port_id")
	@ManyToOne
	@JoinColumn(name = "port_id", referencedColumnName = "id")
	private Port port;

	@Basic
	@Column(name = "port_id", insertable = false, updatable = false)
	private Long portId;

	@Basic
	@Column(name = "product_id", insertable = false, updatable = false)
	private Long productId;

}
