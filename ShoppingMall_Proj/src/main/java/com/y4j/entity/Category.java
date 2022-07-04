package com.y4j.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


  //creating database table
  @Entity
  public class Category implements Serializable 

    {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//creating primary key
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	private String name;
	private String status;
	private String description;
	
	//one category has many products
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Product> product = new HashSet<>();
	
	//here we add product to category
	public void addProduct(Product product) 
	{
		// this will avoid nested cascade
		
		product.setCategory(this);
		this.getProduct().add(product);

		
	}

	//getter and setter
	public Set<Product> getProduct() {
		return product;
	}


	public void setProduct(Set<Product> product) {
		this.product = product;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
