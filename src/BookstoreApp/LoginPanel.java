package BookstoreApp;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(BookstoreApp app, StoreManager manager) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JLabel errorLabel = new JLabel(" ");
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(manager.getAdminUser()) &&
                password.equals(manager.getAdminPass())) {
                app.showScreen("ownerHome");
                return;
            }

            for (StoreCustomer c : manager.getCustomers()) {
                if (c.getUsername().equals(username) &&
                    c.getPassword().equals(password)) {
                    app.setActiveCustomer(c);
                    app.showScreen("customerHome");
                    return;
                }
            }

            errorLabel.setText("Invalid login");
        });

        add(new JLabel("Username"));
        add(usernameField);
        add(new JLabel("Password"));
        add(passwordField);
        add(loginButton);
        add(errorLabel);
    }
}


