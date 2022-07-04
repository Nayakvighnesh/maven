package com.y4j.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.y4j.entity.Product;

@Service
public interface IProductService {

	public List<Product> getAllProduct();
	public void addProduct(Product product);
	public void removeProductById(Long id);
	public Optional<Product> getProductById(Long id);
	public List<Product> getAllProductsByCategoryId(int id);
}
