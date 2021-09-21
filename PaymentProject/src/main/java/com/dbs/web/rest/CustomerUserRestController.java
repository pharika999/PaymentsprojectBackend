package com.dbs.web.rest;



import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Customeruser;
import com.dbs.web.service.CustomerUserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/customeruser")
public class CustomerUserRestController {

	@Autowired
 private CustomerUserService userservice;
	/*@GetMapping("/{userid}")
	public ResponseEntity<Object>  findUserByID(@PathVariable int userid) {
		try { 
			Customeruser user= this.userservice.getUserbyID(userid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(user);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("user with id "+userid+"not found");
		}
	}*/
	@GetMapping("/{username}")
	public ResponseEntity<Object>  findByUsername(@PathVariable String username) {
		try { 
			Customeruser user= this.userservice.findByUsername(username);
			return ResponseEntity.status(HttpStatus.OK)
					.body(user);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("user with id "+username+"not found");
		}
	}
}


