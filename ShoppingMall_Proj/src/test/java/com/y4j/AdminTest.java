package com.y4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.y4j.entity.Admin;
import com.y4j.repository.IAdminRepository;

//created dataJpaTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class AdminTest {
	
	@Autowired
	private IAdminRepository repo; 
	
	//This annotation for test case
	@Test
	public void userFirstname()
	 {
		long id= 8L;
		String name="vighnesh";
		
		    Optional<Admin> user=repo.findById(id);
		    
		   assertThat(user.get().getFirstname()).isEqualTo(name); 
	 }

	@Test
	public void useraNameTest() 
	 {
		
		String username="vighn@gmail.com";
		String password="123456";
		String lastname="Nayak";
		
		Admin user =repo.findByUsernameAndPassword(username, password);
		         assertThat(user.getLastname()).isEqualTo(lastname);
	 }
	
	@Test
	public void userPasswordTest()
	 {
		String username="vighn@gmail.com";
		String password="123456";
		String testpassword="123456";
		
		Admin user =repo.findByUsernameAndPassword(username, password);
		assertThat(user.getPassword()).isEqualTo(testpassword);
	 }
	
	@Test
	public void updateAdminLastName()
	  {
		long id= 1L;
		Admin admin=repo.findById(id).get();
	    
		String lastname="Tendulkar";
		admin.setLastname(lastname);
		
	    Admin updateAdmin  =repo.save(admin);
		          
	    assertThat(updateAdmin.getLastname()).isEqualTo(lastname);
		
	  }
}
	
	
	
