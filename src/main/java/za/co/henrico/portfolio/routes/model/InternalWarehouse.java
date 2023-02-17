package za.co.henrico.portfolio.routes.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@JsonTypeName("INTERNAL")
@DiscriminatorValue("INTERNAL")
@Entity
public class InternalWarehouse extends Warehouse {

	public InternalWarehouse() {
		setType("INTERNAL");
	}

	@Override
	protected BigDecimal getAditionalCost() {
		return BigDecimal.ZERO;
	}

}
