package za.co.henrico.portfolio.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

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

	@Basic
	private String name;

	@Basic
	private Integer capacity;

	@Basic
	private BigDecimal storageCost;

}
