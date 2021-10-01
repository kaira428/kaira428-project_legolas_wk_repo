package com.upskill.legolas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.upskill.legolas.models.User;
import com.upskill.legolas.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository repoUser;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repoUser.findByEmail(username);
		System.out.println(username);						//print email of user for verification. 
		if(user==null)
			throw new UsernameNotFoundException(username);
		
				
		return new CustomUserDetails(user);
	}
	
}
