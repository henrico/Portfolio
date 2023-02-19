package za.co.henrico.portfolio.dto;

import java.math.BigDecimal;

public class WarehouseSimpleDTO {

	private Long id;
	private String name;

	public WarehouseSimpleDTO(Long id, String name, Integer capacity, BigDecimal storageCost, String type, Long portId,
			BigDecimal transportCost) {
		super();
		this.id = id;
		this.name = name;
	}

	public WarehouseSimpleDTO() {
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
}