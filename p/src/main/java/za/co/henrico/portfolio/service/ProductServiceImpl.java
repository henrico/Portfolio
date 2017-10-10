package za.co.henrico.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Product;
import za.co.henrico.portfolio.repository.ProductRepository;
import za.co.henrico.portfolio.repository.ScheduleRepository;

@Component
public class ProductServiceImpl extends AbstractRestServiceImpl<Product> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	protected JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

}
