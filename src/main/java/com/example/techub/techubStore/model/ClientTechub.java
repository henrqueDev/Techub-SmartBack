package com.example.techub.techubStore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "CLIENTS", schema = "techub")
public class ClientTechub {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "CLIENT_NAME")
	private String clientName;

	@Column(name = "CLIENT_CPF", length = 11, unique = true)
	@NotEmpty
	private String cpf;

	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

}
