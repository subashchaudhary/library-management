package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.dao.BookDao;
import dev.subashcodes.librarymangement.exception.DataNotFoundException;
import dev.subashcodes.librarymangement.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {

    @Autowired
    BookDao bookDao;
    public List<Book> getAllBooks() throws DataNotFoundException{

    List<Book> books =  bookDao.fetchAllBooks();
    if(books == null){
        throw new DataNotFoundException("Not Books Available");

    }

    return null;
    }
}
