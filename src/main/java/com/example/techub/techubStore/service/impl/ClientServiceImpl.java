package com.example.techub.techubStore.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.techub.techubStore.model.Client;
import com.example.techub.techubStore.repository.ClientRepository;


@Service
public class ClientServiceImpl {
	
	
	//@Autowired
	//private PasswordEncoder encoder;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Client save(Client user) {
		return clientRepository.save(user);
	}
	
	
	
}
