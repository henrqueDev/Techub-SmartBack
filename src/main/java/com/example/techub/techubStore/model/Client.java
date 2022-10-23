package com.example.techub.techubStore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "CLIENTS", schema = "techub")
public class Client extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="CLIENT_EMAIL",length=200, unique=true)
	@NotEmpty
	@Email
	private String clientEmail;
	
	@Column(name="CLIENT_PASSWORD",length=100, unique=true)
	@NotEmpty
	@Email
	private String clientPassword;

	
	@Builder
	public Client(Integer id, @NotEmpty String clientName,
			@NotEmpty @CPF(message = "Informe um CPF valido!") String cpf, String clientEmail,
			String clientPassword) {
		super(clientName,cpf);
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
		this.id = id;
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientEmail() {
		return clientEmail;
	}


	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPassword() {
		return clientPassword;
	}


	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}


	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
