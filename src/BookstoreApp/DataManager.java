package BookstoreApp;
import java.io.*;
import java.util.*;

public class DataManager {
    private static final String BOOK_FILE = "books.txt";
    private static final String CUSTOMER_FILE = "customers.txt";

    public void saveBooks(List<StoreBook> books) throws IOException {
        FileWriter fw = new FileWriter(BOOK_FILE, false);
        for (StoreBook b : books) {
            fw.write(b.getBookTitle() + "," + b.getBookCost() + "\n");
        }
        fw.close();
    }

    public void saveCustomers(List<StoreCustomer> customers) throws IOException {
        FileWriter fw = new FileWriter(CUSTOMER_FILE, false);
        for (StoreCustomer c : customers) {
            fw.write(c.getUsername() + "," + c.getPassword() + "," + c.getRewardPoints() + "\n");
        }
        fw.close();
    }

    public List<StoreBook> loadBooks() throws IOException {
        List<StoreBook> books = new ArrayList<>();
        File file = new File(BOOK_FILE);

        System.out.println("Loading books from: " + file.getAbsolutePath());

        if (!file.exists()) return books;

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");
            books.add(new StoreBook(data[0].trim(), Double.parseDouble(data[1].trim())));
        }
        sc.close();
        return books;
    }

    public List<StoreCustomer> loadCustomers() throws IOException {
        List<StoreCustomer> customers = new ArrayList<>();
        File file = new File(CUSTOMER_FILE);

        System.out.println("Loading customers from: " + file.getAbsolutePath());

        if (!file.exists()) return customers;

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");
            StoreCustomer c = new StoreCustomer(data[0].trim(), data[1].trim());
            c.updatePoints(Integer.parseInt(data[2].trim()));
            customers.add(c);
        }
        sc.close();
        return customers;
    }
}

