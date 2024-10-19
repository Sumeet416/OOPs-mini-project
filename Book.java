package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private boolean isRented;

    // Constructor
    public Book(int id, String title, String author, double price, boolean isRented) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isRented = isRented;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean isRented() {
        return isRented;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
