import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    private double currentCost;
    private int currentAmountInCart;
    private double cartTotalCost;
    protected List<Product> productsInCart;

    //Constructors
    public ShoppingCart() {
        this.productsInCart = new LinkedList<>();
    }

    //Methods
    //Print the  names of all the product in a current Shopping Cart
    public void printCartProducts(ShoppingCart currentCart) {
        int i = 0;
        System.out.println("Your Shopping Cart contains these products:\n");
        for (Product product : currentCart.productsInCart) {
            i++;
            System.out.println(i + ". " + product.getProductName() + " amount:" + product.getAmount() + "\n");

        }
    }

    //Print the total cost of current Shopping Cart
    public void printTotalCostCart(ShoppingCart currentCart, Customer customer) {
        //calculates the total cost of the cart: this product(with it's discount or not) + the total sum until now
        for (Product product : this.productsInCart) {
            // each rank has different discount
            customer.rankDiscount(customer);
            this.setCurrentCost(this.getCartTotalCost());
            this.setCurrentAmountInCart(product.getAmount());
            // ((price -(price* the discount))*(amount of the product in Cart)
            this.setCartTotalCost(((product.getPrice() - (product.getPrice() * customer.getDiscount())) *
                    (this.getCurrentAmountInCart())));
        }
        System.out.println("The total cost of your Shopping Cart is: " + this.getCartTotalCost());
        //update Customer's cost of purchases
        customer.setCostOfPurchases(this.getCartTotalCost());


    }

    //from given store stock, user puts the product that he chose (by its product number) in the cart

    public ShoppingCart putProductInCart(Store store, Product chosenProduct) {
        //when the product is already in Cart, we need fewer checks
        boolean isAlreadyInCart = false;
        // running on the products in Cart
        for (Product product : this.productsInCart) {
            //if the chosen product is already in the Cart we just need to set its new amount in the Cart and in stock
            if (product.getProductNum() == chosenProduct.getProductNum()) {
                product.setAmount(product.getAmount() + chosenProduct.getAmount());
                isAlreadyInCart = true;
                break;
            }
        }
        // running on store's list of products
        for (Product product : store.productsInStore) {
            //when a chosen productNumber by a customer, is founded in the stock list
            if (product.getProductNum() == chosenProduct.getProductNum()) {
                // reduce the amount of this product from the store when the user adding it to the Cart
                product.setAmount(product.getAmount() - chosenProduct.getAmount());
                //do not need the other operations when its already in Cart
                if (isAlreadyInCart) {
                    return this;
                }
                //set the chosen product's name and price in the Cart
                chosenProduct.setProductName(product.getProductName());
                chosenProduct.setPrice(product.getPrice());
                //add the chosen product to the Cart
                this.productsInCart.add(chosenProduct);
                //remove a product from stock when the amount of product in the store is 0 after adding it to the user's Cart
                if (product.getAmount() == 0) {
                    store.productsInStore.remove(product);
                }

            }
        }
        return this;
    }


    //////////////////////////////////////////////////////////////
    //Getters and Setters


    public double getCurrentCost() {
        return currentCost;
    }

    public int getCurrentAmountInCart() {
        return currentAmountInCart;
    }

    public double getCartTotalCost() {
        return cartTotalCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    public void setCartTotalCost(double cartTotalCost) {
        this.cartTotalCost = cartTotalCost;
    }

    public void setCurrentAmountInCart(int currentAmountInCart) {
        this.currentAmountInCart = currentAmountInCart;
    }


}


