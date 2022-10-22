package com.example.techub.techubStore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CLIENTS", schema = "techub")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	
	@Column(name = "CLIENT_NAME", length = 100)
	@NotEmpty
	private String clientName;
	
	@Column(name="CPF", length = 11)
	@NotEmpty
	private String cpf;
	
	
	public Client(Integer id, String clientName, String cpf) {
		this.id = id;
		this.clientName = clientName;
		this.cpf = cpf;
	}

	public Client(Integer id, String clientName) {
		this.id = id;
		this.clientName = clientName;
	}

	public Client() {}
	
	public Client(String clientName) {
		this.clientName = clientName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String name) {
		this.clientName = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}
