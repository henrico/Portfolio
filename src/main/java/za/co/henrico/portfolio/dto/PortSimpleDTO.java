package za.co.henrico.portfolio.dto;

public class PortSimpleDTO {

	private Long id;

	private String name;

	public PortSimpleDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PortSimpleDTO() {
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
