package za.co.henrico.portfolio.dto;

import java.util.Date;

import za.co.henrico.portfolio.routes.model.OrderStatus;

public class OrderDTO {

	private Long id;

	private PortSimpleDTO destination;
	private Date deliveryDate;
	private ProductDTO product;
	private Integer quantity;
	private OrderStatus orderStatus;

	public PortSimpleDTO getDestination() {
		return destination;
	}

	public void setDestination(PortSimpleDTO destination) {
		this.destination = destination;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
