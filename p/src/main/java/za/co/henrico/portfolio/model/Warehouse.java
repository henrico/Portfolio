package za.co.henrico.portfolio.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

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
	
	@OneToOne
	@JoinColumn(name="port")
	private Port port;

}
