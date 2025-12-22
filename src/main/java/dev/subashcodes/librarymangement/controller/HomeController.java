package dev.subashcodes.librarymangement.controller;

import dev.subashcodes.librarymangement.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class HomeController {

    //this annotatin tells spring to inject the object of Type AddService
    @Autowired
    AddService addService;



    @GetMapping("/add/{num1}/{num2}")
    public int addNum(@PathVariable("num1") int num1, @PathVariable("num2") int num2){

       int result = addService.addNum(num1, num2);

       return result;
    }

    @GetMapping("/home")
    public String home(){


        return "Welcome to Home page";
    }

    @GetMapping("/books")
    public List<String> books(){


        return List.of("Ramayana", "Geeta", "Rich Man", "Physics");


    }
}
