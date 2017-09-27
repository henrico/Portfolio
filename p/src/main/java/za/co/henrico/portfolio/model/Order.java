package za.co.henrico.portfolio.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "orders")
@NamedQuery(name = "Order.findUnfilledOrders", query = "select o from orders o where o.orderStatus = za.co.henrico.portfolio.model.OrderStatus.PLACED"
		+ " and o.quantity > (select COALESCE(sum(s.capacity),0) from Ship s,Schedule sc where s.id = sc.ship.id and sc.order=o)")
public class Order extends AbstractPersistable<Long> {

	@OneToOne
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

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<Schedule> schedules;

	public Port getDestination() {
		return destination;
	}

	public void setDestination(Port destination) {
		this.destination = destination;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
