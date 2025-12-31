package dev.subashcodes.librarymangement.repository;

import dev.subashcodes.librarymangement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
