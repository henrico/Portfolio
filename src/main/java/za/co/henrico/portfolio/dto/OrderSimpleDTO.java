package za.co.henrico.portfolio.dto;

import java.util.Date;

public class OrderSimpleDTO {

	private Long id;
	private Date deliveryDate;
	private ProductDTO product;
	private Integer quantity;
	private PortSimpleDTO destination;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PortSimpleDTO getDestination() {
		return destination;
	}

	public void setDestination(PortSimpleDTO destination) {
		this.destination = destination;
	}

}
