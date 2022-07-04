package com.y4j.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.entity.Product;
import com.y4j.repository.IProductRepository;


@Service
public class IProductServiceImpl  implements IProductService{

	
	@Autowired
	IProductRepository productrepo;
	
	public List<Product> getAllProduct()
	{
		List<Product> prdlist=productrepo.findAll();
		return prdlist;
	}
  
	public void addProduct(Product product){
		productrepo.save(product);
		
	}
	
	public void removeProductById(Long id)
	{
		productrepo.deleteById(id);
	}
	
	public Optional<Product> getProductById(Long id)
	{
		return productrepo.findById(id);
	}
	
	
	public List<Product> getAllProductsByCategoryId(int id)
	{
		return productrepo.findAllByCategoryId(id);
	}
}
