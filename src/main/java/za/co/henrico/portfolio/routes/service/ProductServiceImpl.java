package za.co.henrico.portfolio.routes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.routes.model.Product;
import za.co.henrico.portfolio.routes.repository.ProductRepository;

@Component
public class ProductServiceImpl extends AbstractRestServiceImpl<Product, Long> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	protected JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

}
