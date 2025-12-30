package dev.subashcodes.librarymangement.controller;

import dev.subashcodes.librarymangement.pojo.LoginRequest;
import dev.subashcodes.librarymangement.pojo.SingupRequest;
import dev.subashcodes.librarymangement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest, @RequestHeader HttpHeaders httpHeaders){

        System.out.println("Incomming Request With Unique Id : "+ httpHeaders.getFirst("RequestId"));
        System.out.println("Incomming Request With UserId : "+ httpHeaders.getFirst("UserId"));


        System.out.println("Username: " +  loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());

       boolean isLoginSuccess =  authService.login(loginRequest.getUsername(), loginRequest.getPassword());
       if(isLoginSuccess){
           return "Successfully login";
       }
       return "Invalid Username or Password";

    }



    @PostMapping("/signup")
    public String sinup(@RequestBody SingupRequest singupRequest){
        System.out.println("Signup Request Received for username: " + singupRequest.getUsername());
        boolean isTrue = authService.signup(singupRequest);
        return isTrue ? "signup successful" : "signup failed";
    }
}
