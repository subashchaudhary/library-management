package dev.subashcodes.librarymangement.exception;

public class LibraryMgmtException extends Exception{

    public String message;


    public LibraryMgmtException(String message){
        super(message);
        this.message = message;
    }



}
