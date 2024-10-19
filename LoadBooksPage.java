package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DatabaseConnection;

public class LoadBooksPage extends JFrame {
    private JTextArea booksArea;
    private JButton loadButton, buyBooksButton, rentBooksButton, reviewsButton, signUpButton;

    public LoadBooksPage() {
        setTitle("Load Books");
        setSize(500, 400);  // Increased size for better layout
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a text area to display books
        booksArea = new JTextArea();
        booksArea.setEditable(false); // Make it non-editable
        booksArea.setLineWrap(true);
        booksArea.setWrapStyleWord(true);
        
        // Add a scroll pane to the text area
        JScrollPane scrollPane = new JScrollPane(booksArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create navigation buttons
        loadButton = new JButton("Load Books");
        buyBooksButton = new JButton("Buy Books");
        rentBooksButton = new JButton("Rent Books");
        reviewsButton = new JButton("View Reviews");
        signUpButton = new JButton("Sign Up");

        // Action listeners
        loadButton.addActionListener(e -> loadBooks());
        
        buyBooksButton.addActionListener(e -> {
            new BuyBooksPage().setVisible(true);
            dispose();
        });

        rentBooksButton.addActionListener(e -> {
            new RentBooksPage().setVisible(true);
            dispose();
        });

        reviewsButton.addActionListener(e -> {
            new ReviewPage().setVisible(true);
            dispose();
        });

        signUpButton.addActionListener(e -> {
            new SignUpPage().setVisible(true);
            dispose();
        });

        // Create a panel for buttons with GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10)); // Added gaps between buttons
        buttonPanel.add(loadButton);
        buttonPanel.add(buyBooksButton);
        buttonPanel.add(rentBooksButton);
        buttonPanel.add(reviewsButton);
        buttonPanel.add(signUpButton);

        // Center align the button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBooks() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM books"; // Adjust based on your database structure
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            StringBuilder booksList = new StringBuilder();

            // Display each book's details
            while (resultSet.next()) {
                booksList.append("Title: ").append(resultSet.getString("title"))
                         .append(", Author: ").append(resultSet.getString("author"))
                         .append(", Price: ").append(resultSet.getDouble("price"))
                         .append("\n");
            }
            booksArea.setText(booksList.toString());
            connection.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}

