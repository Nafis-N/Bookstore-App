package BookstoreApp;

public class SilverTier extends CustomerTier {
    @Override
    public String getTierName() {
        return "silver";
    }

    @Override
    public void checkTierUpgrade(StoreCustomer c) {
        if (c.getRewardPoints() >= 1000) {
            c.setTier(new GoldTier());
        }
    }
}


