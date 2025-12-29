package dev.subashcodes.librarymangement.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class SingupRequest {

    private String username;
    private String password;
    private String email;
    private String phone;
}
