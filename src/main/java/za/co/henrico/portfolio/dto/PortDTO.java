package za.co.henrico.portfolio.dto;

import java.util.List;

public class PortDTO {

	private Long id;

	private String name;

	private List<ProductDTO> products;

	public PortDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PortDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
