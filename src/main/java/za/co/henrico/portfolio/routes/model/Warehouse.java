package za.co.henrico.portfolio.routes.model;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = InternalWarehouse.class, name = "INTERNAL"),

		@JsonSubTypes.Type(value = ExternalWarehouse.class, name = "EXTERNAL") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "type")
@Entity
public abstract class Warehouse extends AbstractPersistable<Long> {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getStorageCost() {
		return storageCost;
	}

	public void setStorageCost(BigDecimal storageCost) {
		this.storageCost = storageCost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	@Basic
	private String name;

	@Basic
	private Integer capacity;

	@Basic
	private BigDecimal storageCost;

	@Column(name = "type", nullable = false, updatable = false, insertable = false)
	private String type;

	@ManyToOne
	@JoinColumn(name = "port")
	private Port port;

	@OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<Schedule> schedules;

	@Transient
	public BigDecimal getCost(long days) {
		return (storageCost.multiply(new BigDecimal(days))).add(getAditionalCost());
	}

	@Transient
	protected abstract BigDecimal getAditionalCost();

}
