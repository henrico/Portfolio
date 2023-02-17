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
import jakarta.persistence.OneToMany;

@Entity
public class Product extends AbstractPersistable<Long> {

	@Basic
	private String name;

	@ManyToMany
	@JoinTable(name = "port_products", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "port_id", referencedColumnName = "id"))
	private Collection<Port> ports;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<Order> orders;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
