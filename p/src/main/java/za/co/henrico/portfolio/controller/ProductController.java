package za.co.henrico.portfolio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.henrico.portfolio.model.Product;
import za.co.henrico.portfolio.service.RestService;

@RestController
@RequestMapping("/rest/product")
@CrossOrigin(origins = "*")
public class ProductController extends AbstractAppController<Product> {

	protected RestService getService() {
		return productService;
	}

}
