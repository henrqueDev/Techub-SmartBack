package com.example.techub.techubStore;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techub.techubStore.model.Client;
import com.example.techub.techubStore.repository.ClientRepository;
import com.example.techub.techubStore.service.impl.ClientServiceImpl;

@SpringBootApplication
public class TechubStoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TechubStoreApplication.class, args);
	}

}
