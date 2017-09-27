package za.co.henrico.portfolio.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name="port_products")
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
	@Column(name="port_id",insertable=false,updatable=false)
	private Long portId;
	
	@Basic
	@Column(name="product_id",insertable=false,updatable=false)
	private Long productId;

}
