package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentBooksPage extends JFrame {
    private JTextField bookIdField;
    private JButton rentButton;

    public RentBooksPage() {
        setTitle("Rent Books");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        bookIdField = new JTextField();
        rentButton = new JButton("Rent Book");

        rentButton.addActionListener(new RentListener());

        add(new JLabel("Book ID:"));
        add(bookIdField);
        add(rentButton);
    }

    private class RentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String bookId = bookIdField.getText();
            // Logic to handle book renting (update database)
            JOptionPane.showMessageDialog(null, "Book rented: " + bookId);
        }
    }
}
