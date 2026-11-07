package BookstoreApp;
import java.io.IOException;
import java.util.*;

public class StoreManager {
    private final DataManager db = new DataManager();
    public static List<StoreBook> inventory = new ArrayList<>();
    private List<StoreCustomer> customers = new ArrayList<>();

    public void loadData() throws IOException {
        inventory.clear();
        customers.clear();
        inventory.addAll(db.loadBooks());
        customers.addAll(db.loadCustomers());
    }

    public String getAdminUser() { return "admin"; }
    public String getAdminPass() { return "admin"; }

    public List<StoreCustomer> getCustomers() {
        return customers;
    }

    public List<StoreBook> getInventory() {
        return inventory;
    }

    public void addCustomer(StoreCustomer c) { customers.add(c); }
    public void removeCustomer(StoreCustomer c) { customers.remove(c); }
    public void addBook(StoreBook b) { inventory.add(b); }
    public void removeBook(StoreBook b) { inventory.remove(b); }
}


