package za.co.henrico.portfolio.routes.model;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("INTERNAL")
@DiscriminatorValue("INTERNAL")
@Entity
public class InternalWarehouse extends Warehouse {

	@Override
	protected BigDecimal getAditionalCost() {
		return BigDecimal.ZERO;
	}

}
