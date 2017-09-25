package za.co.henrico.portfolio.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Product;
import za.co.henrico.portfolio.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Collection<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Collection<Product> deleteProduct(long id) {
		productRepository.delete(id);
		return getProducts();
	}

	@Override
	public Collection<Product> saveProduct(Product product) {
		productRepository.save(product);
		return getProducts();
	}

}
