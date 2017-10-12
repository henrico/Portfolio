package za.co.henrico.portfolio.routes.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("EXTERNAL")
@SuppressWarnings("serial")
@DiscriminatorValue("EXTERNAL")
@Entity
public class ExternalWarehouse extends Warehouse {

	@Basic
	private BigDecimal transportCost;

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
