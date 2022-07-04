package com.y4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.y4j.entity.Category;

public interface ICategoryRepository  extends JpaRepository<Category,Integer>{

	
}
