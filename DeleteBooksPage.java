package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import database.DatabaseConnection;

public class DeleteBooksPage extends JFrame {
    private JTextField bookIdField;
    private JButton deleteButton;

    public DeleteBooksPage() {
        setTitle("Delete Books");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        bookIdField = new JTextField();
        deleteButton = new JButton("Delete Book");

        deleteButton.addActionListener(new DeleteListener());

        add(new JLabel("Book ID:"));
        add(bookIdField);
        add(deleteButton);
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String bookId = bookIdField.getText();
            try {
                Connection connection = DatabaseConnection.getConnection();
                String query = "DELETE FROM books WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(bookId));
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book deleted: " + bookId);
                connection.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }
}
