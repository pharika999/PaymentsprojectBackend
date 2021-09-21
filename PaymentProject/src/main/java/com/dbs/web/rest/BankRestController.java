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

import com.dbs.web.beans.Bank;
import com.dbs.web.beans.Customeruser;
import com.dbs.web.service.BankService;
import com.dbs.web.service.CustomerUserService;

@RestController
@CrossOrigin
@RequestMapping("/bank")
public class BankRestController {
	
	@Autowired
	 private BankService bankService ;
		@GetMapping("/{bic}")
		public ResponseEntity<Object>  findUserByID(@PathVariable String bic) {
			try { 
				Bank b= this.bankService.findBankByBIC(bic);
				return ResponseEntity.status(HttpStatus.OK)
						.body(b);
						
				
			}catch (EntityNotFoundException e) {
				System.out.println("error");
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Bank with id "+bic+"not found");
			}
		}

}
