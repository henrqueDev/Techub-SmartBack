package com.example.techub.techubStore.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techub.techubStore.model.ClientTechub;
import com.example.techub.techubStore.repository.ClientTechubRepository;

@Service
public class ClientTechubServiceImpl {
	
	
	@Autowired
	private ClientTechubRepository clientRepository;
	
	@Transactional
	public ClientTechub save(ClientTechub user) {
		return clientRepository.save(user);
	}
	
	
}
