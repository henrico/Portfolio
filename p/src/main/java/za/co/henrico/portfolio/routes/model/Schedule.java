package za.co.henrico.portfolio.routes.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
		@NamedQuery(name = "Schedule.findDateReleventSchedule", query = "select sc from Schedule sc where sc.collectionDate<?1 and sc.deliveryDate >?2 and ship=?3"),
		@NamedQuery(name = "Schedule.findDateReleventScheduleByWarehouse", query = "select sc from Schedule sc where sc.deliveryDate < ?1 and sc.order.deliveryDate > ?2 and warehouse=?3"),
		@NamedQuery(name = "Schedule.findLastScheduleRelatedToShip", query = "select sc from Schedule sc where sc.ship=? order by sc.deliveryDate desc"),
		@NamedQuery(name = "Schedule.findByProduct", query = "select sc from Schedule sc where sc.order.product = ?"),
		@NamedQuery(name = "Schedule.findFirstScheduleRelatedToShipFromDate", query = "select sc from Schedule sc where sc.ship = ?1 and sc.collectionDate >= ?2 order by sc.collectionDate asc") })
public class Schedule extends AbstractPersistable<Long> {

	@ManyToOne
	@JoinColumn(name = "orderF")
	private Order order;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date collectionDate;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;

	@ManyToOne
	@JoinColumn(name = "ship")
	private Ship ship;

	@ManyToOne
	@JoinColumn(name = "source")
	private Port source;

	@ManyToOne
	@JoinColumn(name = "warehouse")
	private Warehouse warehouse;

	@Basic
	private Integer storedCrates;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Port getSource() {
		return source;
	}

	public void setSource(Port source) {
		this.source = source;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getStoredCrates() {
		return storedCrates;
	}

	public void setStoredCrates(int i) {
		this.storedCrates = i;
	}

	@Transient
	public BigDecimal getCost() {
		Calendar c = Calendar.getInstance();
		long days = TimeUnit.DAYS.convert(order.getDeliveryDate().getTime() - deliveryDate.getTime(),
				TimeUnit.MILLISECONDS);

		return warehouse.getCost(days);
	}

}
