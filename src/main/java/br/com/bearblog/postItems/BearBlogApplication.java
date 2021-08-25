package br.com.bearblog.postItems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BearBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BearBlogApplication.class, args);
	}


}
