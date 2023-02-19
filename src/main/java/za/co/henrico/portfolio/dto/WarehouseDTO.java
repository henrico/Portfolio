package za.co.henrico.portfolio.dto;

import java.math.BigDecimal;

public class WarehouseDTO {

	private Long id;
	private String name;
	private Integer capacity;
	private BigDecimal storageCost;
	private String type;
	private Long portId;
	private BigDecimal transportCost;
	private PortSimpleDTO port;

	public WarehouseDTO(Long id, String name, Integer capacity, BigDecimal storageCost, String type, Long portId,
			BigDecimal transportCost) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.storageCost = storageCost;
		this.type = type;
		this.portId = portId;
		this.transportCost = transportCost;
	}

	public WarehouseDTO() {
		super();
	}

	public BigDecimal getTransportCost() {
		return transportCost;
	}

	public void setTransportCost(BigDecimal transportCost) {
		this.transportCost = transportCost;
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getStorageCost() {
		return storageCost;
	}

	public void setStorageCost(BigDecimal storageCost) {
		this.storageCost = storageCost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPortId() {
		return portId;
	}

	public void setPortId(Long portId) {
		this.portId = portId;
	}

	public PortSimpleDTO getPort() {
		return port;
	}

	public void setPort(PortSimpleDTO port) {
		this.port = port;
	}
}