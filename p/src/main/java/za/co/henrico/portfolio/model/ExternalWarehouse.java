package za.co.henrico.portfolio.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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

}
