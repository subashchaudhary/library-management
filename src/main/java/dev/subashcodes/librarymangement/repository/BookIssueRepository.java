package dev.subashcodes.librarymangement.repository;

import dev.subashcodes.librarymangement.model.BookLoan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookIssueRepository extends MongoRepository<BookLoan, String> {

    @Query("{ 'bookId': ?0, 'memberId': ?1 }")
    public BookLoan findByBookIdAndMemberId(String bookId, String memberId);
}
