package za.co.henrico.portfolio.routes.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQuery(name = "Port.findByProductId", query = "select p from Port p where p.id in (select j.portId from port_products j where j.productId=?1)")
public class Port extends AbstractPersistable<Long> {

	@Basic
	private String name;

	@ManyToMany
	@JoinTable(name = "port_products", joinColumns = @JoinColumn(name = "port_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private List<Product> products;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "destinationA", cascade = CascadeType.REMOVE)
	private List<Route> destinationARoutes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "destinationB", cascade = CascadeType.REMOVE)
	private List<Route> destinationBRoutes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "destination", cascade = CascadeType.REMOVE)
	private List<Order> orders;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "port", cascade = CascadeType.REMOVE)
	private Collection<Warehouse> warehouse;

	@OneToMany(mappedBy = "source", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<Schedule> schedules;

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
