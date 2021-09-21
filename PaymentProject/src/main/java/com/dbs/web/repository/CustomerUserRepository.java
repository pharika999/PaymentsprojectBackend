package com.dbs.web.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Customeruser;

public interface CustomerUserRepository extends CrudRepository<Customeruser, Integer> {

	Optional<Customeruser> findByUsername(String username);




}
