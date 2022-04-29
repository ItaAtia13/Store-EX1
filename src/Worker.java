public class Worker extends User{
    private Rank rank;
    private double discount;

public Worker(){

}

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
