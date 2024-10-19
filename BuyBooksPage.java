package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyBooksPage extends JFrame {
    private JTextField bookIdField;
    private JButton buyButton;

    public BuyBooksPage() {
        setTitle("Buy Books");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        bookIdField = new JTextField();
        buyButton = new JButton("Buy Book");

        buyButton.addActionListener(new BuyListener());

        add(new JLabel("Book ID:"));
        add(bookIdField);
        add(buyButton);
    }

    private class BuyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String bookId = bookIdField.getText();
            // Logic to handle book buying (update database)
            JOptionPane.showMessageDialog(null, "Book purchased: " + bookId);
        }
    }
}
