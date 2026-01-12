package dev.subashcodes.librarymangement.controller;

import dev.subashcodes.librarymangement.exception.DataNotFoundException;
import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Book;
import dev.subashcodes.librarymangement.pojo.Response;
import dev.subashcodes.librarymangement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")


    public Response getAllBooks(@RequestHeader HttpHeaders httpHeaders) {
        System.out.println("RequestId: " + httpHeaders.getFirst("RequestId"));

        Response response = new Response();

        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");

        try {
            List<Book> books = bookService.getAllBooks(user, secretCode);

            response.setData(books);
            response.setStatus("Success");
            response.setMessage("Books fetched successfully");
            return response;
        }catch (LibraryMgmtException ex){
            response.setStatus("Failure");
            response.setMessage("Failed to fetch books: " + ex.getMessage());
            return response;
        }

    }

    @GetMapping("/books/{bookId}")
    public Response getBook(@PathVariable("bookId") String bookId, @RequestHeader HttpHeaders httpHeaders) throws DataNotFoundException {
       Response response = new Response();
        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        try{
        Book book =  bookService.getBookById(bookId, user, secretCode);
        response.setStatus("Success");
        response.setMessage("Book fetched successfully for bookId: " + bookId);
        response.setData(book);
        }catch (LibraryMgmtException ex){

            response.setStatus("Failure");
            response.setMessage("Failed to fetch book: " + ex.getMessage());

        }

        return response;

    }


    /**
     * This endpoint is used to add a new book record to the db
     *
     * @param book
     * @return
     */
    @PostMapping("/books/add")
    public String addNewBook(@RequestBody Book book, @RequestHeader HttpHeaders httpHeaders) {
        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        try {
            bookService.addBook(book, user, secretCode);
        }catch (LibraryMgmtException libraryMgmtException){
            return "Failed to add book: " + libraryMgmtException.getMessage();
        }
        return "Book added successfully with bookId: " + book.getId();
    }

    @PutMapping("/books/update/{bookId}")
    public String updateBook(@PathVariable("bookId") String bookId, @RequestBody Book updatedBook, @RequestHeader HttpHeaders httpHeaders) throws DataNotFoundException {

        Response response = new Response();
        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");
        try {
            Book updateBook = bookService.updateBook(bookId, updatedBook, user, secretCode);
            if (updateBook == null) {
                response.setData(null);
                response.setStatus("Failure");
                response.setMessage("Failed to update the book");
            }
            response.setMessage("Book updated successfully for the bookId: " + bookId);
            response.setData(updateBook);
            response.setStatus("Success");


        }catch (LibraryMgmtException libEx){
            response.setMessage("Failed to update book: " + libEx.getMessage());
            response.setStatus("Failure");

        }

        return  null;
    }


    @DeleteMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") String bookId, @RequestHeader HttpHeaders httpHeaders) throws DataNotFoundException {

        String user = httpHeaders.getFirst("User");
        String secretCode = httpHeaders.getFirst("SecretCode");

        try {
            bookService.deleteBook(bookId, user, secretCode);
        }catch (LibraryMgmtException ex){
            return "Failed to delete book: " + ex.getMessage();
        }
        return "Book deleted successfully for bookId: " + bookId;

    }

}
