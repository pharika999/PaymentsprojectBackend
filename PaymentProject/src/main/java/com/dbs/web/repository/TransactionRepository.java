package com.dbs.web.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction,Integer>{

	Optional<Transaction> findById(int transactionId);

	Iterable<Transaction> findAllByCustomerCustomerid(String customerid);


}
