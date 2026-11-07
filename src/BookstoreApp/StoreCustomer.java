package BookstoreApp        ;

public class StoreCustomer {
    private final String username;
    private final String password;
    private int rewardPoints;
    private CustomerTier tier;

    public StoreCustomer(String user, String pass) {
        username = user;
        password = pass;
        rewardPoints = 0;
        tier = new SilverTier();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getRewardPoints() { return rewardPoints; }

    public void updatePoints(int change) {
        rewardPoints += change;
        tier.checkTierUpgrade(this);
    }

    public void setRewardPoints(int points) {
        rewardPoints = points;
        tier.checkTierUpgrade(this);
    }

    public void setTier(CustomerTier newTier) {
        tier = newTier;
    }

    public String getTierName() {
        return tier.getTierName();
    }
}


