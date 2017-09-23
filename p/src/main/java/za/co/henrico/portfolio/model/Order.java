package za.co.henrico.portfolio.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Order extends AbstractPersistable<Long> {

	@OneToMany
	@JoinColumn(name = "destination")
	private Port destination;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;

	@OneToOne
	@JoinColumn(name = "product")
	private Product product;

	@Basic
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

}
