package BookstoreApp;

import javax.swing.*;
import java.awt.*;

public class OwnerHomePanel extends JPanel {
    public OwnerHomePanel(BookstoreApp app) {
        setLayout(new FlowLayout());

        JButton booksBtn = new JButton("Books");
        JButton customersBtn = new JButton("Customers");
        JButton logoutBtn = new JButton("Logout");

        booksBtn.addActionListener(e -> app.showScreen("books"));
        customersBtn.addActionListener(e -> app.showScreen("customers"));
        logoutBtn.addActionListener(e -> app.showScreen("login"));

        add(booksBtn);
        add(customersBtn);
        add(logoutBtn);
    }
}


