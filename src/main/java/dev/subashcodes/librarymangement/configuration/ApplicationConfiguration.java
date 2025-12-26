package dev.subashcodes.librarymangement.configuration;


import dev.subashcodes.librarymangement.util.BookAvailabilityUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {



    @Bean(name = "bookAvailable")
    public BookAvailabilityUtil bookAvailabilityUtil(){
      BookAvailabilityUtil bookAvailabilityUtil =   new BookAvailabilityUtil(false);
      return bookAvailabilityUtil;
    }



}
