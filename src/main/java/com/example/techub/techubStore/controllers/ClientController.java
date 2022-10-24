package com.example.techub.techubStore.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.techub.techubStore.model.Client;
import com.example.techub.techubStore.model.ClientUser;
import com.example.techub.techubStore.repository.ClientRepository;
import com.example.techub.techubStore.service.impl.ClientServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
	
	private ClientServiceImpl clientService;
	private PasswordEncoder passwordEncoder;
	/*
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody Client user) {
		String criptPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(criptPassword);
		System.out.println(user);
		return UserService.save(user);
	}*/
	/*
	@GetMapping("/{id}")
	public Client getClientById( @PathVariable Integer id){
		return clientRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
	}
	
	
	@GetMapping
	public List<Client> find(Client filter) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher( ExampleMatcher.StringMatcher.STARTING);
	
		Example<Client> example = Example.of(filter, matcher);
		
		return clientRepository.findAll(example);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Client> save( @RequestBody @Valid Client user) {
		Client clientToSave = clientRepository.save(user);
		return ResponseEntity.ok(clientToSave);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Client> delete( @PathVariable Integer id){
		Optional<Client> user = clientRepository.findById(id);
		
		if(user.isPresent()) {
			clientRepository.delete( user.get() );
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping(value="/{id}")
	@ResponseBody
	public void update(@PathVariable Integer id, @RequestBody Client user){
		 clientRepository
				.findById(id)
				.map( clientExist -> {
					user.setId(clientExist.getId());
					clientRepository.save(user);
					return clientExist;
		}).orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado") );
		
	}
	*/
	
	
}
