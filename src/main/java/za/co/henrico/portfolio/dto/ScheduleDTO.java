package za.co.henrico.portfolio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ScheduleDTO {

	private Long id;

	private OrderSimpleDTO order;

	private Date collectionDate;

	private Date deliveryDate;

	private ShipSimpleDTO ship;

	private PortSimpleDTO source;

	private WarehouseSimpleDTO warehouse;

	private Integer storedCrates;

	private BigDecimal cost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderSimpleDTO getOrder() {
		return order;
	}

	public void setOrder(OrderSimpleDTO order) {
		this.order = order;
	}

	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public ShipSimpleDTO getShip() {
		return ship;
	}

	public void setShip(ShipSimpleDTO ship) {
		this.ship = ship;
	}

	public PortSimpleDTO getSource() {
		return source;
	}

	public void setSource(PortSimpleDTO source) {
		this.source = source;
	}

	public WarehouseSimpleDTO getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseSimpleDTO warehouse) {
		this.warehouse = warehouse;
	}

	public Integer getStoredCrates() {
		return storedCrates;
	}

	public void setStoredCrates(Integer storedCrates) {
		this.storedCrates = storedCrates;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
