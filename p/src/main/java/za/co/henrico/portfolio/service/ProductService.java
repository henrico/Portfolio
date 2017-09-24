package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Product;

public interface ProductService {

	Collection<Product> getProducts();

	Collection<Product> deleteProduct(long id);

	Collection<Product> saveProduct(Product product);

}
