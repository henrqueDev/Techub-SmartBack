package com.example.techub.techubStore.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.techub.techubStore.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	//select c from CLIENTS c where c.CLIENT_NAME like :CLIENT_NAMe
	
	@Query (value = "select c from CLIENTS c where c.CLIENT_NAME like :CLIENT_NAME")
	List<Client> consultaPorNomeCliente( @Param("CLIENT_NAME") String clientName);
	
	boolean existsByClientName(String nome);
}
