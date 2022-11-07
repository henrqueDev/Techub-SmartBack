package com.example.techub.techubStore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.techub.techubStore.controllers.dto.CredentialsDTO;
import com.example.techub.techubStore.controllers.dto.TokenDTO;
import com.example.techub.techubStore.exception.PasswordNotFoundException;
import com.example.techub.techubStore.model.UserClient;
import com.example.techub.techubStore.security.JwtService;
import com.example.techub.techubStore.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<UserClient> getAllUsers() {
        return this.userService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserClient salvar(@RequestBody @Valid UserClient user) {
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return this.userService.save(user);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredentialsDTO credentials) throws Exception {
        try {
            UserClient user = UserClient.builder()
                    .login(credentials.getLogin())
                    .userPassword(credentials.getUserPassword()).build();
            UserDetails userAutentichated = this.userService.autenticate(user);
            String token = this.jwtService.generateToken(user);
            return new TokenDTO(user.getLogin(), token);
        } catch (UsernameNotFoundException | PasswordNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
