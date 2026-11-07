package BookstoreApp;

public class StoreBook {
    private String bookTitle;
    private double bookCost;

    public StoreBook(String title, double price) {
        this.bookTitle = title;
        this.bookCost = price;
    }

    public String getBookTitle() { return bookTitle; }
    public double getBookCost() { return bookCost; }
}


