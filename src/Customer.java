import java.util.LinkedList;

public class Customer extends User{

    //todo: not sure about these properties
    private boolean hasPurchased;
    private double costOfPurchases;
    private double discount;

    private int Date;

public Customer(){};



//Getters and Setters
public double getCostOfPurchases() {
    return costOfPurchases;
}
    public double getDiscount() {
        return discount;
    }

    public void setHasPurchased(boolean hasPurchased) {
        this.hasPurchased = hasPurchased;
    }

    public void setCostOfPurchases(double costOfPurchases) {
        this.costOfPurchases = costOfPurchases;
    }

    public boolean isMadePurchase() {
        return hasPurchased;
    }



    public void setDiscount(double discount) {
        this.discount = discount;
    }

//Methods


}
