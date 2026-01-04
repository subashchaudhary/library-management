package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.DataNotFoundException;
import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Book;
import dev.subashcodes.librarymangement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {


    @Autowired
    private BookRepository bookRepository;


    //this method is used to add a new book to the db
    public Book addBook(Book book) throws LibraryMgmtException {

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

    public List<Book> getAllBooks(){
        return bookRepository.findAll();

    }

    //this method is used to get the books details by bookId
    public Book getBookById(String bookId) throws DataNotFoundException {

        Optional<Book> bookOptional =  bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new DataNotFoundException("Book not found with id: " + bookId);
        }
        return bookOptional.get();
    }



    public Book updateBook(String bookId, Book updatedBook) throws DataNotFoundException {

        //first check if the book exist with the given bookId
        Book existBook = getBookById(bookId);
        if(existBook != null) {
            //update the book details
            existBook.setTitle(updatedBook.getTitle());
            existBook.setAuthor(updatedBook.getAuthor());
            existBook.setCategory(updatedBook.getCategory());
            existBook.setPublication(updatedBook.getPublication());
            existBook.setAvailable(updatedBook.isAvailable());

            //save the updated book
            bookRepository.save(existBook);
        }
        else {
            throw new DataNotFoundException("Book not found with id: " + bookId);
        }
        return existBook;
    }

    public void deleteBook(String bookId) throws DataNotFoundException {
        //first check if the book exist with the given bookId
        Book existBook = getBookById(bookId);
        if(existBook == null) {
            throw new DataNotFoundException("Book not found with id: " + bookId);
        }

        bookRepository.deleteById(bookId);

    }
}
