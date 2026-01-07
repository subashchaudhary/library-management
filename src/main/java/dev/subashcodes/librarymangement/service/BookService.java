package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.DataNotFoundException;
import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Book;
import dev.subashcodes.librarymangement.model.User;
import dev.subashcodes.librarymangement.repository.BookRepository;
import dev.subashcodes.librarymangement.repository.UserRepository;
import dev.subashcodes.librarymangement.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    //this method is used to add a new book to the db
    public Book addBook(Book book, String user, String secretCode) throws LibraryMgmtException {

        try {
            //validate the user and secret code
            userValidator.checkValidUserAndSecret(user, secretCode);
        }catch (LibraryMgmtException ex){
            throw new LibraryMgmtException("Invalid user or secret code: " + ex.getMessage());
        }



       book.setAvailable(true);

       //chek if the book with the same title and author already exist
        String author = book.getAuthor();
        String title = book.getTitle();
        Optional<Book> bookOptional = bookRepository.findByAuthorAndTitle(author, title);

       if(bookOptional.isPresent()){
           throw new LibraryMgmtException("Book with the same title and author already exist");
       }


       //this is used to save the book to the db
       Book savedBook =  bookRepository.save(book);


       if(savedBook == null){
           throw new LibraryMgmtException("Failed to add the book");
       }

       return savedBook;

    }


    public List<Book> getAllBooks(String user, String secretCode) throws LibraryMgmtException {;
        try {
            //validate the user and secret code
            userValidator.checkValidUserAndSecret(user, secretCode);
        }catch (LibraryMgmtException ex){
            throw new LibraryMgmtException("Invalid user or secret code: " + ex.getMessage());
        }

        return bookRepository.findAll();

    }

    //this method is used to get the books details by bookId
    public Book getBookById(String bookId, String user, String secretCode) throws DataNotFoundException, LibraryMgmtException {

        try {
            //validate the user and secret code
            userValidator.checkValidUserAndSecret(user, secretCode);
        }catch (LibraryMgmtException ex){
            throw new LibraryMgmtException("Invalid user or secret code: " + ex.getMessage());
        }


            try {
                //validate the user and secret code
                userValidator.checkValidUserAndSecret(user, secretCode);
            }catch (LibraryMgmtException ex){
                throw new LibraryMgmtException("Invalid user or secret code: " + ex.getMessage());
            }
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if (bookOptional.isEmpty()) {
                throw new DataNotFoundException("Book not found with id: " + bookId);
            }
            return bookOptional.get();
        }


        public Book updateBook(String bookId, Book updatedBook, String user, String secretCode) throws
        DataNotFoundException, LibraryMgmtException {
            try {
                //validate the user and secret code
                userValidator.checkValidUserAndSecret(user, secretCode);
            }catch (LibraryMgmtException ex){
                throw new LibraryMgmtException("Invalid user or secret code: " + ex.getMessage());
            }

            try {
                //first check if the book exist with the given bookId
                Book existBook = getBookById(bookId, user, secretCode);
                if (existBook != null) {
                    //update the book details
                    existBook.setTitle(updatedBook.getTitle());
                    existBook.setAuthor(updatedBook.getAuthor());
                    existBook.setCategory(updatedBook.getCategory());
                    existBook.setPublication(updatedBook.getPublication());
                    existBook.setAvailable(updatedBook.isAvailable());

                    //save the updated book
                    bookRepository.save(existBook);
                } else {
                    throw new DataNotFoundException("Book not found with id: " + bookId);
                }
                return existBook;
            } catch (LibraryMgmtException ex) {
                throw new LibraryMgmtException("Failed to update book: " + ex.getMessage());
            }
        }


        public void deleteBook(String bookId, String user, String secretCode) throws LibraryMgmtException {
            try {
                //validate the user and secret code
                userValidator.checkValidUserAndSecret(user, secretCode);
            }catch (LibraryMgmtException ex){
                throw new LibraryMgmtException("Invalid user or secret code: " + ex.getMessage());
            }

            try {
                //validate the user and secret code
                userValidator.checkValidUserAndSecret(user, secretCode);
            } catch (LibraryMgmtException ex) {
                throw new LibraryMgmtException("Invalid user or secret code: " + ex.getMessage());
            }


        }

}
