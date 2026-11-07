package BookstoreApp;

public class GoldTier extends CustomerTier {
    @Override
    public String getTierName() {
        return "gold";
    }

    @Override
    public void checkTierUpgrade(StoreCustomer c) {
        if (c.getRewardPoints() < 1000) {
            c.setTier(new SilverTier());
        }
    }
}


