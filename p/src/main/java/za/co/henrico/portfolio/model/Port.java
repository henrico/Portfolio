package za.co.henrico.portfolio.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Port extends AbstractPersistable<Long> {

	@Basic
	private String name;

	@ManyToMany
	@JoinTable(name = "port_products", joinColumns = @JoinColumn(name = "port_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private List<Product> products;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "destinationA",cascade=CascadeType.REMOVE)
	private List<Route> destinationARoutes;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "destinationB",cascade=CascadeType.REMOVE)
	private List<Route> destinationBRoutes;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "destination",cascade=CascadeType.REMOVE)
	private List<Order> orders;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "port",cascade=CascadeType.REMOVE)
	private Warehouse warehouse;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
