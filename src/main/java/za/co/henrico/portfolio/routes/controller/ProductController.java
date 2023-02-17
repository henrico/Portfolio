package za.co.henrico.portfolio.routes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.routes.model.Product;
import za.co.henrico.portfolio.routes.service.RestService;

@RestController
@RequestMapping("/rest/product")

public class ProductController extends AbstractAppController<Product, Long> {

	public ProductController() {
//		throw new RuntimeException();
	}

	protected RestService<Product, Long> getService() {
		return productService;
	}

}
