package za.co.henrico.portfolio.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;

@SuppressWarnings("serial")
@DiscriminatorValue("EXTERNAL")
public class ExternalWarehouse extends Warehouse {

	@Basic
	private BigDecimal transportCost;

}
