package dev.subashcodes.librarymangement.exception;

import dev.subashcodes.librarymangement.util.DateTimeFormatter;

public class DataNotFoundException extends Exception{


    public DataNotFoundException(String message){
        super(message);
    }
}
