import java.util.LinkedList;

public class Customer extends User{

    //todo: not sure about these properties
    private boolean hasPurchased;
    private double costOfPurchases;
    private int Date;

public Customer(){};

    public void setHasPurchased(boolean hasPurchased) {
        this.hasPurchased = hasPurchased;
    }

    public boolean isMadePurchase() {
        return hasPurchased;
    }

    public void setCostOfPurchases(double costOfPurchases) {
        this.costOfPurchases = costOfPurchases;
    }

    public double getCostOfPurchases() {
        return costOfPurchases;
    }
//Methods


}
