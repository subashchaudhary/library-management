package dev.subashcodes.librarymangement.dao;


import dev.subashcodes.librarymangement.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDao {

    public List<Book> fetchAllBooks(){

        //TO-DO: fetch all the Books from DB.

        Book b1 = new Book("C++", "sabin rana", "sabin publication");
        Book b2 = new Book("Java", "Utkirsh rana", "Utrkish publication");
        Book b3 = new Book("React", "Amar", "Amar publication");

        List<Book> books = new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);
        return books;

    }
}
