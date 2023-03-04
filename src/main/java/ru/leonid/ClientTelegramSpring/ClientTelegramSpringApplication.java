package ru.leonid.ClientTelegramSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ClientTelegramSpringApplication{
	public static void main(String[] args) {
		SpringApplication.run(ClientTelegramSpringApplication.class, args);
	}
}
