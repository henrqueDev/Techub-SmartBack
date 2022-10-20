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

@SpringBootApplication
@RestController
public class TechubStoreApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClientRepository clients) {
		return args -> {
			//System.out.println("Salvando clientes");
            //clients.save(new Client("Fulano"));
            //clients.save(new Client("Outro Cliente"));
            clients.save(new Client("Sion astroneuta"));
            
            //List<Client> consulta = clients.consultaPorNomeCliente("Dougllas123456");
            //boolean existe = clients.existsByClientName("Dougllas");
            //System.out.println("existe um cliente com o nome Dougllas? " + existe);
            //consulta.forEach(System.out::println);
		};
	}
	
	@Value("${application.name}")
	private String applicationName;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return applicationName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TechubStoreApplication.class, args);
	}

}
