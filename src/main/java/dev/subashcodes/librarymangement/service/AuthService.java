package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.User;
import dev.subashcodes.librarymangement.pojo.SingupRequest;
import dev.subashcodes.librarymangement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {


    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password){

        //validate the request
        if(username == null || password == null){
            return null;
        }
       User user = userRepository.findByUsernameAndPassword(username, password);
        if(user == null){
            return null;
        }
        return user;
    }


    public User signup(SingupRequest singupRequest) throws LibraryMgmtException{

        String email = singupRequest.getEmail();
        String password = singupRequest.getPassword();
        String username = singupRequest.getUsername();
        String phone = singupRequest.getPhone();

        //Entity mapping
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
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
