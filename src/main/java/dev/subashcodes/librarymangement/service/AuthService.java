package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.model.User;
import dev.subashcodes.librarymangement.pojo.SingupRequest;
import dev.subashcodes.librarymangement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {


    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password){


        return true;
    }


    public boolean signup(SingupRequest singupRequest){

        //validate the request


        String email = singupRequest.getEmail();
        String password = singupRequest.getPassword();
        String username = singupRequest.getUsername();
        String phone = singupRequest.getPhone();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUsername(username);

        User savedUser = userRepository.save(user);
        if(savedUser == null){
            return false;
        }

        return true;

    }
}
