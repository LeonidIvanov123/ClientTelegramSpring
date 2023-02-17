package ru.leonid.ClientTelegramSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ClientTelegramSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientTelegramSpringApplication.class, args);
	}

}
