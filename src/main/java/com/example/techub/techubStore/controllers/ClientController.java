package com.example.techub.techubStore.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.techub.techubStore.model.Client;
import com.example.techub.techubStore.repository.ClientRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	private ClientRepository clientRepository;
	
	
	
	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Client> getClientById( @PathVariable Integer id){
		Optional<Client> clientFound = clientRepository.findById(id);
		
		if(clientFound.isPresent()) {
			//HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok(clientFound.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Client>> find(Client filter) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher( ExampleMatcher.StringMatcher.STARTING);
	
		Example<Client> example = Example.of(filter, matcher);
		
		List<Client> clientsFound = clientRepository.findAll(example);
		return ResponseEntity.ok(clientsFound);
		
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Client> save( @RequestBody() Client user) {
		Client clientToSave = clientRepository.save(user);
		return ResponseEntity.ok(clientToSave);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseBody
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
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Client user){
		return clientRepository
				.findById(id)
				.map( clientExist -> {
					user.setId(clientExist.getId());
					clientRepository.save(user);
					return ResponseEntity.noContent().build();
		}).orElseGet( () -> ResponseEntity.notFound().build());
		
	}
	
	
	
}
