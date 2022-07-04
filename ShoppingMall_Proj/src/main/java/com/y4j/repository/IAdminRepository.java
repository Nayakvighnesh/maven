package com.y4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.y4j.entity.Admin;



public interface IAdminRepository extends JpaRepository<Admin, Long>{
	Admin findByUsernameAndPassword(String username, String password);
 
}