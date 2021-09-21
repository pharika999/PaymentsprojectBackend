package com.dbs.web.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Employee;
import com.dbs.web.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	public Employee getEmployeeByID(int id) {
		try {
		 Optional<Employee> emp = this.empRepo.findById(id);
			return emp.orElseThrow(()->{
				return new EntityNotFoundException("Employee with"+id + " does not exist");
			});

		}catch(IllegalArgumentException e )
		{
			return null;
		}
	}


}
