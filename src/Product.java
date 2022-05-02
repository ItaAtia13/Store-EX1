public class Product {
    private String productName;
    private int productNum;
    private double price;
    private int amount;
    private int discountPrecent;
    private boolean isInStock;

    //Constructors
    public Product() {
    }


    public Product(int productNum) {
        this.productNum = productNum;
    }

    //Getters
    public String getProductName() {
        return productName;
    }

    public int getProductNum() {
        return productNum;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscountPrecent() {
        return discountPrecent;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public int getAmount() {
        return amount;
    }
    //Setters

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void setDiscountPrecent(int discountPrecent) {
        this.discountPrecent = discountPrecent;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
