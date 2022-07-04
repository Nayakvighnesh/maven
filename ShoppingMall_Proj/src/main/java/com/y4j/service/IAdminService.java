package com.y4j.service;

import org.springframework.stereotype.Service;

import com.y4j.entity.Admin;



@Service
public interface IAdminService {
	public Admin login(String username, String password);
	public void addAdmin(Admin login);

}