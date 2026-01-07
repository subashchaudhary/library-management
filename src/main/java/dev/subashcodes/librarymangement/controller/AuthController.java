package dev.subashcodes.librarymangement.controller;

import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.User;
import dev.subashcodes.librarymangement.pojo.LoginRequest;
import dev.subashcodes.librarymangement.pojo.Response;
import dev.subashcodes.librarymangement.pojo.SignupResponse;
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

    /**
     * This is the login api endpoint
     *
     * @param loginRequest
     * @param httpHeaders
     * @return
     */
    @PostMapping("/login") // Mapping HTTP POST requests to /login
    @ResponseBody // To indicate that the return value should be bound to the web response body
    public Response login(@RequestBody LoginRequest loginRequest, @RequestHeader HttpHeaders httpHeaders){

        Response response = new Response();

       System.out.println("Incomming Request With Unique Id : "+ httpHeaders.getFirst("RequestId"));

       //validate the login request
        if(loginRequest.getUsername() == null || loginRequest.getPassword() == null){
            response.setStatus("Failure");
            response.setMessage("Username or Password cannot be null");
        }

       User user =  authService.login(loginRequest.getUsername(), loginRequest.getPassword());
       if(user != null){
           String message =  "Successfully login";
           response.setStatus("Success");
           response.setMessage(message);
           response.setData(user);
       }
       return response;

    }



    @PostMapping("/signup")
    public Response sinup(@RequestBody SingupRequest singupRequest){
        System.out.println("Signup Request Received for username: " + singupRequest.getUsername());

        Response response = new Response();
        //validate the input data

        try {
            validateSignupRequest(singupRequest);


            User user = authService.signup(singupRequest);

            response.setStatus("Success");
            response.setMessage("Successfully signed up");
            SignupResponse signupResponse = new SignupResponse();
            signupResponse.setPassword(user.getPassword());
            signupResponse.setUsername(user.getUsername());
            signupResponse.setUserId(user.getId());
            signupResponse.setSecretCode(user.getSecretCode());

            response.setData(signupResponse);

        }catch (LibraryMgmtException libraryMgmtException){

            response.setStatus("Failure");
            String message =  "Signup failed: " + libraryMgmtException.getMessage();
            response.setMessage(message);
        }

        return response;

    }

    private void validateSignupRequest(SingupRequest singupRequest) throws LibraryMgmtException{
        // Add validation logic here (e.g., check for null or empty fields, validate email format, etc.)

        String email = singupRequest.getEmail();
        String password = singupRequest.getPassword();
        String username = singupRequest.getUsername();
        String phone = singupRequest.getPhone();

        if(email == null || email.isEmpty()){
            throw new LibraryMgmtException("Email cannot be null or empty");
        }

        if(password == null || password.isEmpty()){
            throw new LibraryMgmtException("Password cannot be null or empty");
        }
        if(username == null || username.isEmpty()){
            throw new LibraryMgmtException("Username cannot be null or empty");
        }

        if(phone != null && !phone.matches("\\d{10}")){
            throw new LibraryMgmtException("Phone number must be 10 digits");
        }
    }
}
