package dev.subashcodes.librarymangement.controller;

import dev.subashcodes.librarymangement.exception.DataNotFoundException;
import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Book;
import dev.subashcodes.librarymangement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();

    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable("bookId") String bookId) throws DataNotFoundException {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/books/add")
    public String addNewBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
        }catch (LibraryMgmtException libraryMgmtException){
            return "Failed to add book: " + libraryMgmtException.getMessage();
        }
        return "Book added successfully with bookId: " + book.getId();
    }

    @PutMapping("/books/update/{bookId}")
    public String updateBook(@PathVariable("bookId") String bookId, @RequestBody Book updatedBook) throws DataNotFoundException {

       Book updateBook =  bookService.updateBook(bookId, updatedBook);
       if(updateBook == null) {
           return "Failed to update the book";
       }
        return "Book updated successfully for the bookId: " + bookId;


    }


    @DeleteMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") String bookId) throws DataNotFoundException {
        try {
            bookService.deleteBook(bookId);
        }catch (DataNotFoundException dataNotFoundException){
            return "Failed to delete book: " + dataNotFoundException.getMessage();
        }
        return "Book deleted successfully for bookId: " + bookId;

    }

}
