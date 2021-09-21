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

import com.dbs.web.beans.Employee;
import com.dbs.web.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/{empid}")
	public ResponseEntity<Object>  findEmployeeByID(@PathVariable int empid) {
		try { 
			Employee emp= this.empService.getEmployeeByID(empid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(emp);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("user with id "+empid+"not found");
		}
	}

}
