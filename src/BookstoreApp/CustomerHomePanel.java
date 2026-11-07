package BookstoreApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerHomePanel extends JPanel {
    private StoreManager manager;
    private StoreCustomer activeCustomer;
    private JLabel infoLabel;
    private JTable table;
    private DefaultTableModel model;

    public CustomerHomePanel(BookstoreApp app, StoreManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());

        infoLabel = new JLabel(" ");
        add(infoLabel, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"Select", "Book Name", "Price"}, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return Boolean.class;
                if (column == 2) return Double.class;
                return String.class;
            }
        };

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton buyBtn = new JButton("Buy");
        JButton redeemBtn = new JButton("Redeem Points & Buy");
        JButton logoutBtn = new JButton("Logout");

        buyBtn.addActionListener(e -> processPurchase(app, false));
        redeemBtn.addActionListener(e -> processPurchase(app, true));
        logoutBtn.addActionListener(e -> app.showScreen("login"));

        JPanel bottom = new JPanel();
        bottom.add(buyBtn);
        bottom.add(redeemBtn);
        bottom.add(logoutBtn);

        add(bottom, BorderLayout.SOUTH);
    }

    public void setActiveCustomer(StoreCustomer c) {
        activeCustomer = c;
    }

    public void refreshTable() {
        if (activeCustomer == null) return;

        infoLabel.setText("Welcome " + activeCustomer.getUsername() +
                ". You have " + activeCustomer.getRewardPoints() +
                " points. Your status is " + activeCustomer.getTierName() + ".");

        model.setRowCount(0);
        for (StoreBook b : manager.getInventory()) {
            model.addRow(new Object[]{false, b.getBookTitle(), b.getBookCost()});
        }
    }

    private void processPurchase(BookstoreApp app, boolean redeem) {
        double total = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean selected = (Boolean) model.getValueAt(i, 0);
            if (selected != null && selected) {
                total += (double) model.getValueAt(i, 2);
            }
        }

        if (total == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one book.");
            return;
        }

        double discount = 0;
        if (redeem) {
            discount = activeCustomer.getRewardPoints() / 100.0;
            if (discount > total) discount = total;
            activeCustomer.updatePoints(-(int)(discount * 100));
        }

        double finalCost = total - discount;
        int earnedPoints = (int) finalCost * 10;
        activeCustomer.updatePoints(earnedPoints);

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            Boolean selected = (Boolean) model.getValueAt(i, 0);
            if (selected != null && selected) {
                String title = (String) model.getValueAt(i, 1);
                double price = (double) model.getValueAt(i, 2);

                for (StoreBook b : StoreManager.inventory) {
                    if (b.getBookTitle().equals(title) && b.getBookCost() == price) {
                        StoreManager.inventory.remove(b);
                        break;
                    }
                }
            }
        }

        app.getReceiptPanel().setReceipt(finalCost, activeCustomer);
        app.showScreen("receipt");
    }
}


