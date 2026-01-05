package dev.subashcodes.librarymangement.controller;


import dev.subashcodes.librarymangement.exception.LibraryMgmtException;
import dev.subashcodes.librarymangement.model.Book;
import dev.subashcodes.librarymangement.model.BookLoan;
import dev.subashcodes.librarymangement.pojo.IssueBookRequest;
import dev.subashcodes.librarymangement.pojo.Response;
import dev.subashcodes.librarymangement.service.BookIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller used to provides all the API related to book issue to the members
 * of the library management system.
 *
 *
 */
@RestController
public class BookIssueController {


    @Autowired
    private BookIssueService bookIssueService;

    @PostMapping("/issueBook")
    public Response issueBookToMember(@RequestBody IssueBookRequest issueBookRequest) {

        System.out.println("Incomming Request to Issue new Book");
        Response response = new Response();
        try{
           BookLoan bookLoan =  bookIssueService.issueBook(issueBookRequest.getBookId(), issueBookRequest.getMemberId());
            response.setStatus("Success");
            response.setMessage("Book issued successfully to memberId: " + issueBookRequest.getMemberId());
            response.setData(bookLoan);
        }catch (LibraryMgmtException ex){
            String message =  "Failed to issue book: " + ex.getMessage();
            response.setMessage(message);
            response.setStatus("Failure");
        }
        return response;
    }



    @PostMapping("/returnBook")
    public Response returnBook(@RequestBody IssueBookRequest issueBookRequest) {

        Response response = new Response();
        try{
            BookLoan book =  bookIssueService.returnBook(issueBookRequest.getBookId(), issueBookRequest.getMemberId());
            response.setStatus("Success");
            response.setMessage("Book returned successfully for memberId: " + issueBookRequest.getMemberId());
            response.setData(book);
        }catch (LibraryMgmtException ex){
            String message =  "Failed to return book: " + ex.getMessage();
            response.setMessage(message);
            response.setStatus("Failure");
        }
        return response;


    }
}
