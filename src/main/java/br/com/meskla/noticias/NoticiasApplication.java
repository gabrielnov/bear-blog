package br.com.meskla.noticias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class NoticiasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoticiasApplication.class, args);
	}


}
