package dev.subashcodes.librarymangement.util;

import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.User;
import dev.subashcodes.librarymangement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {


    @Autowired
    private UserRepository userRepository;

    public boolean checkValidUserAndSecret(String user, String secretCode) throws LibraryMgmtException {

//        if (user == null || secretCode == null || user.isEmpty() || secretCode.isEmpty()) {
//            throw new LibraryMgmtException("Invalid user or secret code");
//        }
//
//        //check if the user and secret code are valid
//
//        User existUser =  userRepository.findByUsername(user);
//        if(existUser == null){
//            throw new LibraryMgmtException("User not found: " + user);
//        }
//
//        if(!existUser.getSecretCode().equals(secretCode)){
//            throw new LibraryMgmtException("Invalid secret code for user: " + user);
//        }
        return true;
    }
}
