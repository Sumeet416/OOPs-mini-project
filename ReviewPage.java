package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReviewPage extends JFrame {
    private JComboBox<String> bookComboBox;
    private JTextArea reviewTextArea;
    private JButton submitButton;
    private JLabel feedbackLabel;

    public ReviewPage() {
        setTitle("Submit a Review");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        bookComboBox = new JComboBox<>();
        loadBooks(); // Load books into the combo box

        reviewTextArea = new JTextArea(5, 20);
        submitButton = new JButton("Submit Review");
        feedbackLabel = new JLabel();

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitReview();
            }
        });

        // Create a panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));
        inputPanel.add(new JLabel("Select Book:"));
        inputPanel.add(bookComboBox);
        inputPanel.add(new JLabel("Write your review:"));
        inputPanel.add(new JScrollPane(reviewTextArea));

        // Add components to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
        add(feedbackLabel, BorderLayout.NORTH);
    }

    private void loadBooks() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT title FROM books"; // Adjust as necessary
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate the combo box with book titles
            while (resultSet.next()) {
                bookComboBox.addItem(resultSet.getString("title"));
            }
            connection.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + ex.getMessage());
        }
    }

    private void submitReview() {
        String selectedBook = (String) bookComboBox.getSelectedItem();
        String reviewText = reviewTextArea.getText();

        if (reviewText.isEmpty()) {
            feedbackLabel.setText("Please write a review.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO reviews (book_id, review_text) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Get the book ID based on the selected title (this assumes a mapping method)
            int bookId = getBookIdByTitle(selectedBook); 

            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, reviewText);
            preparedStatement.executeUpdate();

            feedbackLabel.setText("Review submitted successfully!");
            reviewTextArea.setText(""); // Clear the text area

            connection.close();
        } catch (Exception ex) {
            feedbackLabel.setText("Error submitting review: " + ex.getMessage());
        }
    }

    private int getBookIdByTitle(String title) {
        // Implement this method to return the book ID based on the title
        // You may want to query the database to find the corresponding ID
        // For simplicity, let's return a fixed ID (you need to implement this correctly)
        // Example: return 1; 
        return 1; // Replace this with the actual logic
    }
}
