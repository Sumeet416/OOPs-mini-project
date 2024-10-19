package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignUpPage extends JFrame {
    private JTextField usernameField, passwordField, emailField;
    private JButton signUpButton;

    public SignUpPage() {
        setTitle("Sign Up");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();
        signUpButton = new JButton("Sign Up");

        signUpButton.addActionListener(new SignUpListener());

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Email:"));
        add(emailField);
        add(signUpButton);
    }

    private class SignUpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            // Database connection and insert logic here
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_bookstore", "root", "");
                String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sign Up Successful!");
                connection.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }
}
