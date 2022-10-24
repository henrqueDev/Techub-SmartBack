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
import com.example.techub.techubStore.model.ClientUser;
import com.example.techub.techubStore.security.JwtService;
import com.example.techub.techubStore.service.impl.ClientUserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private ClientUserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientUser salvar( @RequestBody @Valid ClientUser usuario ){
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return userService.save(usuario);
    }

    
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredentialsDTO credenciais) throws Exception{
        try{
        	ClientUser usuario = ClientUser.builder()
                    .login(credenciais.getLogin())
                    .password(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = userService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | PasswordNotFoundException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
