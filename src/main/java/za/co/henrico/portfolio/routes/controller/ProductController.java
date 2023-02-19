package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.dto.ProductDTO;
import za.co.henrico.portfolio.routes.model.Product;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/product")

public class ProductController extends AbstractAppController<Product, Long, ProductDTO> {

	public ProductController() {
//		throw new RuntimeException();
	}

	protected RestService<Product, Long> getService() {
		return productService;
	}

	@Override
	protected Class<Product> getEntityClass(ProductDTO productDTO) {
		return Product.class;
	}

	@Override
	protected Class<ProductDTO> getDtoClass(Product product) {
		return ProductDTO.class;
	}

	@Override
	protected Product mapChildren(Product entity, ProductDTO dto) {

		return entity;
	}

	@Override
	protected void applyMappingRules() {
	}

}
