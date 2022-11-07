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
import com.example.techub.techubStore.model.UserClient;
import com.example.techub.techubStore.repository.UserRepository;

import java.util.List;
import java.util.StringJoiner;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public List<UserClient> getAll() {
        return this.repository.findAll();
    }

    @Transactional
    public UserClient save(UserClient user) {
        return repository.save(user);
    }

    public UserDetails autenticate(UserClient user) throws Exception {
        UserDetails userFound = loadUserByUsername(user.getLogin());
        System.out.println(user.getPassword() + " AUEEEEEE");
        System.out.println(userFound.getPassword() + " ueeeeEE");
        boolean passwordsMatches = encoder.matches(user.getPassword(), userFound.getPassword());
        if (passwordsMatches) {
            return userFound;
        } else {
            throw new Exception();
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserClient user = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = user.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

        return User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

}