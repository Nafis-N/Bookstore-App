package BookstoreApp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BookstoreApp extends JFrame {
    private StoreManager manager;
    private DataManager data;
    private StoreCustomer activeCustomer;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private LoginPanel loginPanel;
    private OwnerHomePanel ownerHomePanel;
    private BooksPanel booksPanel;
    private CustomersPanel customersPanel;
    private CustomerHomePanel customerHomePanel;
    private ReceiptPanel receiptPanel;

    public BookstoreApp() {
        manager = new StoreManager();
        data = new DataManager();

        try {
            manager.loadData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data");
        }

        setTitle("Bookstore App");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.out.println("Window is closing...");
                saveOnExit();
                System.exit(0);
            }
        });

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this, manager);
        ownerHomePanel = new OwnerHomePanel(this);
        booksPanel = new BooksPanel(this, manager);
        customersPanel = new CustomersPanel(this, manager);
        customerHomePanel = new CustomerHomePanel(this, manager);
        receiptPanel = new ReceiptPanel(this);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(ownerHomePanel, "ownerHome");
        mainPanel.add(booksPanel, "books");
        mainPanel.add(customersPanel, "customers");
        mainPanel.add(customerHomePanel, "customerHome");
        mainPanel.add(receiptPanel, "receipt");

        add(mainPanel);
        showScreen("login");
    }

    public void showScreen(String name) {
        if (name.equals("books")) booksPanel.refreshTable();
        if (name.equals("customers")) customersPanel.refreshTable();
        if (name.equals("customerHome")) customerHomePanel.refreshTable();
        cardLayout.show(mainPanel, name);
    }

    public void setActiveCustomer(StoreCustomer c) {
        activeCustomer = c;
        customerHomePanel.setActiveCustomer(c);
    }

    public StoreCustomer getActiveCustomer() {
        return activeCustomer;
    }

    public StoreManager getManager() {
        return manager;
    }

    public ReceiptPanel getReceiptPanel() {
        return receiptPanel;
    }

    private void saveOnExit() {
        try {
            System.out.println("Books in memory: " + StoreManager.inventory.size());
            System.out.println("Customers in memory: " + manager.getCustomers().size());
            data.saveBooks(StoreManager.inventory);
            data.saveCustomers(manager.getCustomers());
            System.out.println("Saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookstoreApp app = new BookstoreApp();
            app.setVisible(true);
        });
    }
}


