package za.co.henrico.portfolio.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Product;
import za.co.henrico.portfolio.repository.ProductRepository;
import za.co.henrico.portfolio.repository.ScheduleRepository;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;

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
	@Transactional
	public Collection<Product> saveProduct(Product product) {
		
		productRepository.save(product);
		return getProducts();
	}

}
