package com.dbs.web.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Bank;
import com.dbs.web.beans.Customer;
import com.dbs.web.repository.BankRespository;

@Service
public class BankService {
	
	@Autowired
	private BankRespository bankRepo;
	
	public Bank findBankByBIC(String id)
	{
		try {
			Optional<Bank> bank = this.bankRepo.findById(id);

			return bank.orElseThrow(()->{
				
				return new EntityNotFoundException("Bank with "+id + " does not exist");
			});


		}catch(IllegalArgumentException e )
		{
			return null;
		}
	}
}
