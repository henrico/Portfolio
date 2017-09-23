package za.co.henrico.portfolio.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "type")
public abstract class Warehouse extends AbstractPersistable<Long> {

	@Basic
	private String name;

	@Basic
	private Integer capacity;

	@Basic
	private BigDecimal storageCost;

}
