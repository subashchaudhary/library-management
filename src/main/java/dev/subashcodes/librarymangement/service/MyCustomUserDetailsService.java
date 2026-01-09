package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.model.User;
import dev.subashcodes.librarymangement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //createing our customer user details

        User user =  userRepository.findByUsername(username);
        MyUserDetails myUserDetails = new MyUserDetails(user);
        return myUserDetails;
    }
}
