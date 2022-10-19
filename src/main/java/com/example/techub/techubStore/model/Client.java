package com.example.techub.techubStore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTS", schema = "techub")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	public Client(Integer id, String clientName) {
		super();
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
	
	
}
