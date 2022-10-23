package com.example.techub.techubStore.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;

public abstract class User {

	@Column(name = "CLIENT_NAME", length = 100)
	@NotEmpty
	private String clientName;
	
	@Column(name="CLIENT_CPF", length = 11, unique=true)
	@NotEmpty
	@CPF(message = "Informe um CPF valido!")
	private String cpf;

	public User(@NotEmpty String clientName, @NotEmpty @CPF(message = "Informe um CPF valido!") String cpf) {
		this.clientName = clientName;
		this.cpf = cpf;
	}
	

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	
}
