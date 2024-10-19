package model;

public class Review {
    private int id;
    private int bookId;
    private int userId;
    private String review;

    // Constructor
    public Review(int id, int bookId, int userId, String review) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.review = review;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getUserId() {
        return userId;
    }

    public String getReview() {
        return review;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
