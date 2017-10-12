package za.co.henrico.portfolio.routes.model;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = InternalWarehouse.class, name = "INTERNAL"),

		@JsonSubTypes.Type(value = ExternalWarehouse.class, name = "EXTERNAL") })
@SuppressWarnings("serial")
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
