package BookstoreApp;

public abstract class CustomerTier {
    public abstract String getTierName();
    public abstract void checkTierUpgrade(StoreCustomer c);
}


