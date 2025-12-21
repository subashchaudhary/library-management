package com.hubit.librarymangement;

import com.hubit.librarymangement.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@SpringBootApplication
public class LibrarymangementApplication {


	public static void main(String[] args) {
		SpringApplication.run(LibrarymangementApplication.class, args);

        System.out.println("Application Started...");


    //    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibrarymangementApplication.class);
   //     AddService addService = (AddService) applicationContext.getBean("addService");
   //     System.out.println(addService);
        //object creation and calling method
//        AddService addService = new AddService();


//        int result = addService.addNum(4, 23);
//        System.out.println("Sum = :" + result);

	}
//http://localhost:8080/
}
