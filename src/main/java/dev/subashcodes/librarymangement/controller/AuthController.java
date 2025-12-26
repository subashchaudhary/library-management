package dev.subashcodes.librarymangement.controller;

import dev.subashcodes.librarymangement.pojo.LoginRequest;
import dev.subashcodes.librarymangement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){

        System.out.println("Username: " +  loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());

       boolean isLoginSuccess =  authService.login(loginRequest.getUsername(), loginRequest.getPassword());
       if(isLoginSuccess){
           return "Successfully login";
       }
       return "Invalid Username or Password";

    }
}
