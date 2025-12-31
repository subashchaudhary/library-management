package dev.subashcodes.librarymangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories
@SpringBootApplication
public class LibrarymangementApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(LibrarymangementApplication.class, args);
        System.out.println("Application Started...");

	}
}
