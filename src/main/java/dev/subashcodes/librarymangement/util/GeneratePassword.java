package dev.subashcodes.librarymangement.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeneratePassword {

    public static String encrpytedPassword(String text){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(text);
    }


    public static void main(String[] args) {
        String password = encrpytedPassword("Password@123456789");
        System.out.println(password);
    }
}
