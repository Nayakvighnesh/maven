package com.y4j.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.y4j.entity.Product;

public interface IProductRepository extends JpaRepository<Product,Long> {
    
	
	List<Product> findAllByCategoryId(int id);

}
