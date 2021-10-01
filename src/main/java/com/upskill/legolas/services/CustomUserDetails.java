package com.upskill.legolas.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.upskill.legolas.models.User;

public class CustomUserDetails implements UserDetails {	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public CustomUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
	//	return Collections.singleton(new SimpleGrantedAuthority("USER"));
		return null;
	}

	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getFullname() {
		// TODO Auto-generated method stub
		return user.getFirst_name()+ ", "+ user.getLast_name();
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		
		return user.getEmail();
	}
}
