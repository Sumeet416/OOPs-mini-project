
import javax.swing.*;
import view.LoginPage;  // Importing the LoginPage class

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginPage().setVisible(true);  // This should work if everything is set up correctly
        });
    }
}
