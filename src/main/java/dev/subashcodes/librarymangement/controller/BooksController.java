//package dev.subashcodes.librarymangement.controller;
//
//import dev.subashcodes.librarymangement.exception.DataNotFoundException;
//import dev.subashcodes.librarymangement.model.Book;
//import dev.subashcodes.librarymangement.service.BookService;
//import dev.subashcodes.librarymangement.util.BookAvailabilityUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class BooksController {
//
//    @Autowired
//    private BookService bookService;
//
//    @Autowired
//    private BookAvailabilityUtil bookAvailabilityUtil;
//
//    @GetMapping(value = "/books")
//    public List<Book> getBooks(){
//        List<Book> books = null;
//        System.out.println("Incoming Request to fetch books.");
//        try {
//          books  = bookService.getAllBooks();
//        }catch (DataNotFoundException dataNotFoundException){
//            System.out.print("Exception Occurs: "+ dataNotFoundException);
//        }
//
//        return books;
//    }
//
//    @GetMapping("/books/{bookId}")
//    public Book getBook(@PathVariable("bookId") String bookId){
//
//        return null;
//    }
//}
