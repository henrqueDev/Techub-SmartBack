package com.example.techub.techubStore.controllers;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserClient salvar( @RequestBody @Valid UserClient usuario ){
        String senhaCriptografada = this.passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return this.userService.save(usuario);
    }

    
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredentialsDTO credenciais) throws Exception{
        try{
        	UserClient usuario = UserClient.builder()
                    .login(credenciais.getLogin())
                    .userPassword(credenciais.getSenha()).build();
            this.userService.autenticar(usuario);
            String token = this.jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | PasswordNotFoundException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
