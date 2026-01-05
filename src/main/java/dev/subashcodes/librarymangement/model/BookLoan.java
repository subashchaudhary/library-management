package dev.subashcodes.librarymangement.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "loans")
public class BookLoan {

    @MongoId
    private String id = UUID.randomUUID().toString();

    // We store IDs as references
    private String bookId;
    private String memberId;
    private String memberName;

    private LocalDate issueDate = LocalDate.now();
    private LocalDate returnDate; // Null if not yet returned

    private float fineAmount = 0.0f;

    public float getFineAmount() {
        return fineAmount;
    }
    public void setFineAmount(float fineAmount) {
        this.fineAmount = fineAmount;
    }

    private boolean isReturned = false;

    public boolean isReturned() {
        return isReturned;
    }
    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    // Standard Getters/Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}