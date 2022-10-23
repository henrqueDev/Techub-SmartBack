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
public class ClientServiceImpl implements UserDetailsService {
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Client save(Client user) {
		return clientRepository.save(user);
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client user = clientRepository.queryLogin(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = user.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(user.getClientName())
                .password(user.getClientPassword())
                .roles(roles)
                .build();
    }
	
	public UserDetails loadUserByLogin(String email, String password) throws UsernameNotFoundException {
		Client user = clientRepository.queryLogin(email, password)
				.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
		
		return User
				.builder()
				.username(user.getClientEmail())
				.password(user.getClientPassword())
				.build();
	
	} 
	
}
