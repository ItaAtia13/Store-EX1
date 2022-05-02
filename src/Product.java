public class Product {
    private String productName;
    private int productNum;
    private double price;
    private int amount;

    private int discountPrecent;
    //todo:not sure about this field
    private boolean isInStock;

    //Constructors
    public Product(){
    };

    //todo: check if i need to add amount
    public Product(int productNum) {
        this.productNum=productNum;
    }

//todo: dont know if to make constructor
//    public Product(String productName, int productNum, int price, boolean isInStock, int amount) {
//        this.productName = productName;
//        this.productNum = productNum;
//        this.price = price;
//        this.isInStock = isInStock;
//        this.amount = amount;
//    }

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
