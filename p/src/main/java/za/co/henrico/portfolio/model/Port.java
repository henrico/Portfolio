package za.co.henrico.portfolio.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

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
