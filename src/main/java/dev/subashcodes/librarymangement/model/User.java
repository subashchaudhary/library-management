package dev.subashcodes.librarymangement.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class User {

    //primary key
    @MongoId
    private String username;
    private String password;
    private String email;
    private String phone;

  //  private String role;


}
