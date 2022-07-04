package com.y4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.y4j.entity.Admin;
import com.y4j.repository.IAdminRepository;

//admin service class
@Service
public class IAdminServiceImpl implements IAdminService {
	
	@Autowired
	private IAdminRepository repo;

public Admin login(String username, String password) {
	 Admin user = repo.findByUsernameAndPassword(username, password);
	return user;
 }



public void addAdmin(Admin login)
{
	repo.save(login);
}
	

}
