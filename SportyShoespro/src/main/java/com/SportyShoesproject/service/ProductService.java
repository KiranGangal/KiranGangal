package com.SportyShoesproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SportyShoesproject.model.Product;
import com.SportyShoesproject.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product addProductWithUser(Product product) {
		return productRepository.save(product);
	}
	
	public Optional<Product> getProductById(int id) {
		Optional<Product> proOptional = productRepository.findById(id);
		return proOptional;
	}
	
	public Optional<Product> getProductByName(String name) {
		Optional<Product> proOptional = productRepository.findByName(name);
		return proOptional;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> getAllProductBasedOnCatogary(String category) {
		return productRepository.findAllByCategory(category);
	}

	public void deleteProductById(int prdId) {
		productRepository.deleteById(prdId);
	}

}
