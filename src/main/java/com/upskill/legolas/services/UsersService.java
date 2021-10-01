package com.upskill.legolas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.upskill.legolas.models.User;
import com.upskill.legolas.repositories.UserRepository;

@Service
public class UsersService {

	@Autowired
	private UserRepository userRepo;
	
	public Page<User> listAll(int pageNumber, String sortField, String sortDir, String keyword){
		
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber-1, 5,sort);
		System.out.println(pageable);
		
		if(keyword!=null) {
			return userRepo.findAll(keyword,pageable);
		}
		
		return userRepo.findAll(pageable);
	}
	
	public void save(User user) {
		
		userRepo.save(user);
	}
	
	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public void delete(Long id) {
		userRepo.deleteById(id);;
	}
	
}
