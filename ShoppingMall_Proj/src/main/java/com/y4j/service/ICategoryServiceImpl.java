package com.y4j.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.entity.Category;
import com.y4j.repository.ICategoryRepository;

@Service
public class ICategoryServiceImpl implements ICategoryService {

  @Autowired
  ICategoryRepository categoryrepo;
  
  
  public void addCategory(Category cat)
	{
	  categoryrepo.save(cat);
	}

	public List<Category> getAllCategory()
	{
		List<Category> catlist=categoryrepo.findAll(); 
		return catlist;
		
	}
	
	public void removeCategoryById(int id)
	{
		categoryrepo.deleteById(id);
	}
	
	public Optional<Category> getCategoryById(int id)
	{
		return categoryrepo.findById(id);
	}
  
}
