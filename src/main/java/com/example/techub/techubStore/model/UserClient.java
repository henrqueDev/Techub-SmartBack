package com.example.techub.techubStore.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserClient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "login", length = 100, unique = true)
	@NotEmpty
	private String login;

	@Column(name = "user_password", length = 100, unique = true)
	@NotEmpty
	private String userPassword;

	@Column(name = "user_admin")
	private boolean userAdmin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return userPassword;
	}

	public void setPassword(String password) {
		this.userPassword = password;
	}

	public boolean isAdmin() {
		return userAdmin;
	}

	public boolean getUserAdmin() {
		return this.userAdmin;
	}

	public void setUserAdmin(boolean admin) {
		this.userAdmin = admin;
	}

}
