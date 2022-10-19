package com.example.techub.techubStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techub.techubStore.model.User;
import com.example.techub.techubStore.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void saveUser(User user) {
		validarUser(user);
		this.repository.persist(user);
	}
	
	public void validarUser(User user) {
		
	}
}
