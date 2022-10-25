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

import com.example.techub.techubStore.model.ClientTechub;
import com.example.techub.techubStore.repository.ClientTechubRepository;
import com.example.techub.techubStore.service.impl.ClientTechubServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

	private ClientTechubRepository clientRepository;
	
	 @GetMapping("{id}")
	    public ClientTechub getClienteById( @PathVariable Integer id ){
	        return clientRepository
	                .findById(id)
	                .orElseThrow(() ->
	                        new ResponseStatusException(HttpStatus.NOT_FOUND,
	                                "Cliente não encontrado"));
	    }

	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public ClientTechub save( @RequestBody @Valid ClientTechub cliente ){
	        return clientRepository.save(cliente);
	    }

	    @DeleteMapping("{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void delete( @PathVariable Integer id ){
	        clientRepository.findById(id)
	                .map( cliente -> {
	                    clientRepository.delete(cliente );
	                    return cliente;
	                })
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	                        "Cliente não encontrado") );

	    }

	    @PutMapping("{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void update( @PathVariable Integer id,
	                        @RequestBody @Valid ClientTechub cliente ){
	        clientRepository
	                .findById(id)
	                .map( clienteExistente -> {
	                    cliente.setId(clienteExistente.getId());
	                    clientRepository.save(cliente);
	                    return clienteExistente;
	                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	                    "Cliente não encontrado") );
	    }

	    @GetMapping
	    public List<ClientTechub> find( ClientTechub filtro ){
	        ExampleMatcher matcher = ExampleMatcher
	                                    .matching()
	                                    .withIgnoreCase()
	                                    .withStringMatcher(
	                                            ExampleMatcher.StringMatcher.CONTAINING );

	        Example<ClientTechub> example = Example.of(filtro, matcher);
	        return clientRepository.findAll(example);
	    }
	
	
}