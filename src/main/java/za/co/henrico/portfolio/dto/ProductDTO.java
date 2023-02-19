package za.co.henrico.portfolio.dto;

public class ProductDTO {
	
    private Long id;
    private String name;
    
	public ProductDTO() {
		super();
	}
	public ProductDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}