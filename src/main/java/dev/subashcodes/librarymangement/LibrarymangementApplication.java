package dev.subashcodes.librarymangement;

import dev.subashcodes.librarymangement.service.AddService;
import dev.subashcodes.librarymangement.util.BookAvailabilityUtil;
import dev.subashcodes.librarymangement.util.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class LibrarymangementApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(LibrarymangementApplication.class, args);

        System.out.println("Application Started...");

       BookAvailabilityUtil bookAvailabilityUtil = (BookAvailabilityUtil) applicationContext.getBean("bookAvailable");
    //    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibrarymangementApplication.class);
        System.out.println(bookAvailabilityUtil);
        AddService addService = (AddService) applicationContext.getBean("addService");
        int result = addService.addNum(4, 23);
        System.out.println("Sum = :" + result);

	}
//http://localhost:8080/
}
