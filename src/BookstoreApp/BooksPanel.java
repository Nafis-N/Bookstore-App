package BookstoreApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BooksPanel extends JPanel {
    private StoreManager manager;
    private DefaultTableModel model;
    private JTable table;

    public BooksPanel(BookstoreApp app, StoreManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Book Name", "Price"}, 0);
        table = new JTable(model);

        JTextField titleField = new JTextField(10);
        JTextField priceField = new JTextField(10);

        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        addBtn.addActionListener(e -> {
            try {
                manager.addBook(new StoreBook(
                    titleField.getText(),
                    Double.parseDouble(priceField.getText())
                ));
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String title = (String) model.getValueAt(row, 0);
                double price = (double) model.getValueAt(row, 1);

                for (StoreBook b : StoreManager.inventory) {
                    if (b.getBookTitle().equals(title) && b.getBookCost() == price) {
                        manager.removeBook(b);
                        break;
                    }
                }
                refreshTable();
            }
        });

        backBtn.addActionListener(e -> app.showScreen("ownerHome"));

        JPanel top = new JPanel();
        top.add(new JLabel("Name"));
        top.add(titleField);
        top.add(new JLabel("Price"));
        top.add(priceField);
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
        for (StoreBook b : manager.getInventory()) {
            model.addRow(new Object[]{b.getBookTitle(), b.getBookCost()});
        }
    }
}


