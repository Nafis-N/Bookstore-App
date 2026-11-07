package BookstoreApp;

import javax.swing.*;
import java.awt.*;

public class ReceiptPanel extends JPanel {
    private JLabel costLabel;
    private JLabel infoLabel;

    public ReceiptPanel(BookstoreApp app) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        costLabel = new JLabel(" ");
        infoLabel = new JLabel(" ");
        JButton logoutBtn = new JButton("Logout");

        logoutBtn.addActionListener(e -> app.showScreen("login"));

        add(costLabel);
        add(infoLabel);
        add(logoutBtn);
    }

    public void setReceipt(double finalCost, StoreCustomer customer) {
        costLabel.setText("Total Cost: $" + String.format("%.2f", finalCost));
        infoLabel.setText("Points: " + customer.getRewardPoints() +
                " | Status: " + customer.getTierName());
    }
}


