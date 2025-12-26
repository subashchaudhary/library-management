package dev.subashcodes.librarymangement.model;

public class Book {
    public Book(String bookName, String authorName, String publication) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.publication = publication;
    }

    public String bookName;
    public String authorName;
    public String publication;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }
}
