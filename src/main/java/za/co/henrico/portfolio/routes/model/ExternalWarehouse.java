package za.co.henrico.portfolio.routes.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Basic;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@JsonTypeName("EXTERNAL")
@DiscriminatorValue("EXTERNAL")
@Entity
public class ExternalWarehouse extends Warehouse {

	@Basic
	private BigDecimal transportCost;

	public ExternalWarehouse() {
		setType("EXTERNAL");
	}

	public BigDecimal getTransportCost() {
		return transportCost;
	}

	public void setTransportCost(BigDecimal transportCost) {
		this.transportCost = transportCost;
	}

	@Override
	protected BigDecimal getAditionalCost() {
		return transportCost;
	}

}
