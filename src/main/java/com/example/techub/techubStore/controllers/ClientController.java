package com.example.techub.techubStore.controllers;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.techub.techubStore.model.Client;
import com.example.techub.techubStore.repository.ClientRepository;

@Controller
@RequestMapping("/api/clients")
public class ClientController {
	
	private ClientRepository clientRepository;
	
	
	
	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}


	@RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public String helloClient( @PathVariable("nome") String clientName) {
		return String.format("Hello %s", clientName);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getClientById( @PathVariable Integer id){
		Optional<Client> clientFound = clientRepository.findById(id);
		
		if(clientFound.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			ResponseEntity<Client> response = new ResponseEntity<>(clientFound.get(), HttpStatus.OK);
			return ResponseEntity.ok(clientFound.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
