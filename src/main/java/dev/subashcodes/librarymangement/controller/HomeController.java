package dev.subashcodes.librarymangement.controller;

import dev.subashcodes.librarymangement.service.AddService;
import dev.subashcodes.librarymangement.util.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HomeController {

    //this annotation tells spring to inject the object of Type AddService
    @Autowired
    AddService addService;

    @Autowired
    DateTimeFormatter dateTimeFormatter;

    //GET Mapping with Path varible
    @GetMapping("/add/{num1}/{num2}")
    public int addNum(@PathVariable("num1") int num1, @PathVariable("num2") int num2){

       int result = addService.addNum(num1, num2);

       return result;
    }

    //GET Mapping
    @GetMapping("/home")
    public String home(){


        return "Welcome to Home page";
    }


    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable("userId") String userId){

        return ("Welcome user : " + userId);
    }

    //GET mapping using query param(Request param)
    @GetMapping("/about")
    public String about(@RequestParam("username") String username,
                        @RequestParam("password") String password){


        return "welcome to about page : with username :" + username + " and password : " + password;
    }
}
