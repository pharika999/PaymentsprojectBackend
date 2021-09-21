 package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Customer;

import com.dbs.web.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	public List<Customer> getCustomers()
	{
		List<Customer> customers = new ArrayList<Customer>();
		this.repo.findAll().forEach(cust->customers.add(cust));
		return customers;
	}
	public boolean insertCustomer(Customer customer)
	{
		if(this.repo.findById(customer.getCustomerid()).isPresent())
			return false;
		try {
			this.repo.save(customer);
		}catch(IllegalArgumentException e )
		{
			return false;
		}
		return true;
	}

	public Customer findCustomerById(String id)
	{
		try {
			Optional<Customer> cust = this.repo.findById(id);

			return cust.orElseThrow(()->{
				
				return new EntityNotFoundException("Customer with "+id + " does not exist");
			});


		}catch(IllegalArgumentException e )
		{
			return null;
		}

	}
	public boolean deleteCustomer(String id) throws Exception
	{
		try {
			this.repo.deleteById(id);
		}catch(IllegalArgumentException e )
		{
			throw new IllegalArgumentException("Could not delete");
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
		return true;
	}
	public boolean updateCustomer(Customer cust)
	{
		try {
			this.repo.save(cust);
		}catch(IllegalArgumentException e )
		{
			return false;
		}
		return true;
	}
	
}
	
