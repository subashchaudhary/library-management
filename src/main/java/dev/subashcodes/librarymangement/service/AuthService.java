package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.User;
import dev.subashcodes.librarymangement.pojo.Response;
import dev.subashcodes.librarymangement.pojo.SingupRequest;
import dev.subashcodes.librarymangement.repository.UserRepository;
import dev.subashcodes.librarymangement.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtility jwtUtility;


    @Autowired
    private AuthenticationManager authenticationManager;

    public Response login(String username, String password){

        Response response = new Response();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(userDetails == null) {
            return null;
        }
        response.setMessage("Success");
        response.setMessage("Successfully logged in");
        String token = jwtUtility.generateToken(userDetails.getUsername());
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        response.setData(data);
        return response;
    }


    public User signup(SingupRequest singupRequest) throws LibraryMgmtException{

        String email = singupRequest.getEmail();
        String password = singupRequest.getPassword();
        String username = singupRequest.getUsername();
        String phone = singupRequest.getPhone();

        //Entity mapping
        User user = new User();
        user.setEmail(email);

        //encrypt the password before saving to database

        user.setPhone(phone);
        user.setUsername(username);
        //This is used to generate a unique secret code for the user
        String secretCode = "LIB-" + System.currentTimeMillis();
        user.setSecretCode(secretCode);

        //this will save the user to the database if saved it will return the saved user object else if not saved
        //it will return null
        User savedUser = userRepository.save(user);
        if(savedUser == null){
            throw new LibraryMgmtException("Failed to signup user");
        }

        return savedUser;

    }
}
