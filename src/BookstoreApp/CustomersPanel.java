package BookstoreApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomersPanel extends JPanel {
    private StoreManager manager;
    private DefaultTableModel model;
    private JTable table;

    public CustomersPanel(BookstoreApp app, StoreManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Username", "Password", "Points"}, 0);
        table = new JTable(model);

        JTextField userField = new JTextField(10);
        JTextField passField = new JTextField(10);

        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        addBtn.addActionListener(e -> {
            manager.addCustomer(new StoreCustomer(userField.getText(), passField.getText()));
            refreshTable();
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String user = (String) model.getValueAt(row, 0);
                for (StoreCustomer c : manager.getCustomers()) {
                    if (c.getUsername().equals(user)) {
                        manager.removeCustomer(c);
                        break;
                    }
                }
                refreshTable();
            }
        });

        backBtn.addActionListener(e -> app.showScreen("ownerHome"));

        JPanel top = new JPanel();
        top.add(new JLabel("Username"));
        top.add(userField);
        top.add(new JLabel("Password"));
        top.add(passField);
        top.add(addBtn);

        JPanel bottom = new JPanel();
        bottom.add(deleteBtn);
        bottom.add(backBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public void refreshTable() {
        model.setRowCount(0);
        for (StoreCustomer c : manager.getCustomers()) {
            model.addRow(new Object[]{c.getUsername(), c.getPassword(), c.getRewardPoints()});
        }
    }
}


