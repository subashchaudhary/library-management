package dev.subashcodes.librarymangement.service;

import dev.subashcodes.librarymangement.exception.DataNotFoundException;
import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Book;
import dev.subashcodes.librarymangement.model.BookLoan;
import dev.subashcodes.librarymangement.model.Member;
import dev.subashcodes.librarymangement.repository.BookIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component

public class BookIssueService {

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookIssueRepository bookIssueRepository;



    public BookLoan issueBook(String bookId, String memberId) throws LibraryMgmtException{

        //step 1: check if book is exist and available

        try {
          Book book =  bookService.getBookById(bookId);
          if(book == null){
                throw new LibraryMgmtException("Book not found with id: " + bookId);
          }

          //step 2: check if the book is available
            if(!book.isAvailable()){
                throw new LibraryMgmtException("Book is not available for issue with id: " + bookId);
            }

          // step 3: check if the member is eligible to issue the book
           Member member = memberService.getMemberInfo(memberId); // will throw exception if member not found
              if(member == null){
                throw new LibraryMgmtException("Member not found with id: " + memberId);
              }

              if(!member.isActive()) {
                throw new LibraryMgmtException("Member is not active with id: " + memberId);
            }

          String memberName =  member.getFirstName() + " " + member.getLastName();
          //step 4: now issue the book

            BookLoan bookLoan = new BookLoan();
            bookLoan.setBookId(bookId);
            bookLoan.setMemberId(memberId);
            bookLoan.setIssueDate(LocalDate.now());
            bookLoan.setReturnDate(LocalDate.now().plusDays(15)); // due in 15 days

            //set member name
            bookLoan.setMemberName(memberName);

            //step 5: save the book issue record and mark the book as not available
            bookIssueRepository.save(bookLoan);

            //one book issued, now mark the book as not available


            book.setAvailable(false);
            bookService.updateBook(bookId, book);


            return bookLoan;

        } catch (DataNotFoundException e) {

            throw new LibraryMgmtException("Failed to issue book: " + e.getMessage());
        }

    }

    public BookLoan returnBook(String bookId, String memberId) throws LibraryMgmtException {
        //step 1: find the book issue record
        BookLoan bookLoan = bookIssueRepository.findByBookIdAndMemberId(bookId, memberId);
        if(bookLoan == null){
            throw new LibraryMgmtException("No active book issue record found for bookId: " + bookId + " and memberId: " + memberId);
        }

        //step 2: mark the book as returned

        // calculate fine
        LocalDate today = LocalDate.now();

        LocalDate returnDate = bookLoan.getReturnDate();

        if(today.isAfter(returnDate)){
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(returnDate, today);
            float fineAmount = daysLate * 5;
            bookLoan.setFineAmount(fineAmount);
        } else {
            bookLoan.setFineAmount(0f);
        }

        //set return flag as true
        bookLoan.setReturned(true);
        bookIssueRepository.save(bookLoan);

        //step 3: mark the book as available
        try {
            Book book = bookService.getBookById(bookId);
            book.setAvailable(true);
            bookService.updateBook(bookId, book);
        } catch (DataNotFoundException e) {
            throw new LibraryMgmtException("Failed to update book availability: " + e.getMessage());
        }

        return bookLoan;
    }

}
