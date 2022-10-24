package com.example.techub.techubStore.exception;

public class PasswordNotFoundException extends RuntimeException {
	
	static final long serialVersionUID = 1L;

	public PasswordNotFoundException() {
        super("Senha inválida");
    }
	
}
