package com.dbs.web.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Customer;

import com.dbs.web.service.CustomerService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/customers")
public class CustomerRestController {
	
	@Autowired
	private CustomerService custService;
	@GetMapping
	public List<Customer> findCustomers()
	{
		return this.custService.getCustomers();
	}
	@PostMapping
	public ResponseEntity<String> insertCustomer(@RequestBody Customer customer)
	{
		if(this.custService.insertCustomer(customer))
			return  ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Customer inserted with id "+customer.getCustomerid());
		return  ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Customer not inserted with id "+customer.getCustomerid());

	}
	 @GetMapping("/{custid}")
		public ResponseEntity<Object> findCustomerById(@PathVariable String custid)
		{
			try { 
				Customer cust= this.custService.findCustomerById(custid);
				return ResponseEntity.status(HttpStatus.OK)
						.body(cust);
						
				
			}catch (EntityNotFoundException e) {
				System.out.println("error");
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Customer with id "+custid+"not found");
			}
		}
	 @PutMapping
		public ResponseEntity<String> updateCustomer(@RequestBody Customer customer)
		{
			if(this.custService.updateCustomer(customer))
				return  ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body("Customer updated with id "+customer.getCustomerid());
			return  ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Customer not updated with id "+customer.getCustomerid());
		}
	  @DeleteMapping("/{custid}")
		public  ResponseEntity<String> deleteCustomerById(@PathVariable String custid)
		{
			try {
				if(this.custService.deleteCustomer(custid))
					return  ResponseEntity
							.status(HttpStatus.ACCEPTED)
							.body("Customer deleted with id "+custid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return  ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Customer not deleted with id "+custid+"\n\n"+e.getMessage());
			}
			return  ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Customer not deleted with id "+custid);
		}
	}
