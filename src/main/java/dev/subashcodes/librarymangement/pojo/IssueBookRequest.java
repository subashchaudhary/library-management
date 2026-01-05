package dev.subashcodes.librarymangement.pojo;

public class IssueBookRequest {

    private String memberId;
    private String bookId;

    public IssueBookRequest() {
    }

    public IssueBookRequest(String memberId, String bookId) {
        this.memberId = memberId;
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
