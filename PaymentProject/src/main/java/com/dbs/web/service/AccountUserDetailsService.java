package com.dbs.web.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Customeruser;
import com.dbs.web.repository.CustomerUserRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomerUserRepository userRepo;
	
	 @Autowired
	private PasswordEncoder encode;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Customeruser> optional = this.userRepo.findByUsername(username);
        return optional.map(account->{
            User user = new User(account.getUserName(), encode.encode(account.getUserPassword()),
           Arrays.asList(new SimpleGrantedAuthority("USER")));
            return user;
        }).orElseThrow(()-> new UsernameNotFoundException("Invalid Credentials"));
	}
	
	  @Bean
	    public PasswordEncoder encoder()
	    {
	    	return new BCryptPasswordEncoder();
	    }

}
