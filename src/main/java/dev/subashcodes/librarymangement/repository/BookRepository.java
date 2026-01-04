package dev.subashcodes.librarymangement.repository;

import dev.subashcodes.librarymangement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {


    //Method query can be defined here if needed

    @Query("{ 'author': ?0, 'title': ?1 }")
    public Optional<Book> findByAuthorAndTitle(String author, String title);


    @Query("{ 'title': ?0 }")
    public Book findByTitle(String title);
}
