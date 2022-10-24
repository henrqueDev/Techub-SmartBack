package com.example.techub.techubStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.techub.techubStore.model.ClientUser;
import com.example.techub.techubStore.repository.ClientUserRepository;

import java.util.StringJoiner;

@Service
public class ClientUserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ClientUserRepository repository;

    @Transactional
    public ClientUser save(ClientUser user){
        return repository.save(user);
    }

    public UserDetails autenticar(ClientUser user ) throws Exception{
        UserDetails userFound = loadUserByUsername(user.getLogin());
        boolean senhasBatem = encoder.matches( user.getPassword(), user.getPassword() );

        if(senhasBatem){
            return userFound;
        }

        throw new Exception();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientUser usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getPassword())
                .roles(roles)
                .build();
    }

}