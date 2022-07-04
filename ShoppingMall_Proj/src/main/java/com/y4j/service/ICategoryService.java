package com.y4j.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.y4j.entity.Category;

@Service
public interface ICategoryService {

	public void addCategory(Category cat);
	public List<Category> getAllCategory();
	public void removeCategoryById(int id);
	public Optional<Category> getCategoryById(int id);
}
