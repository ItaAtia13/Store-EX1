import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart{
    private int cartTotalCost;
    //todo:check if protected problematic
    protected List <Product> productsInCart;

    //Constructors
    public ShoppingCart() {
        this.productsInCart=new LinkedList<>();
    }

//    public ShoppingCart(List<Product> productsInCart) {
//        this.productsInCart = productsInCart;
//    }
//
//    public ShoppingCart(int cartTotalCost, List<Product> productsInCart) {
//        this.cartTotalCost = cartTotalCost;
//        this.productsInCart = productsInCart;
//    }

    //Methods
    //Print the  names of all the product in a current Shopping Cart
    public void printCartProducts(ShoppingCart currentCart){
        int i=0;
        System.out.println("Your Shopping Cart contains these products:\n");
        for (Product product:currentCart.productsInCart) {
            i++;
            System.out.println(i+". "+product.getProductName()+" amount:"+product.getAmount()+"\n");

        }
    }

    //Print the total cost of current Shopping Cart
    public void printTotalCostCart(ShoppingCart currentCart){
        //when Cart is empty its current total cost is 0
        int currentCost=0;
        //calculates the total cost of the cart: this product + the total sum until now
        for (Product product:this.productsInCart) {
            this.setCartTotalCost((product.getPrice()*product.getAmount()+currentCost));
            currentCost=this.getCartTotalCost();
        }
//        if (user.getUserType().equals("Worker")){
//            //for example total cost before discount is 100 so: 100 - (0.1*100)= the total cost after discount
//            this.setCartTotalCost(this.getCartTotalCost()-(user.rankDiscount(user)*this.getCartTotalCost()));
//            System.out.println("After your discount,the total cost of your Shopping Cart is: "+this.getCartTotalCost());
//
//        }else {
            System.out.println("The total cost of your Shopping Cart is: " + this.getCartTotalCost());

    }



    //todo:does this func should be ShoppingCart or List<Product>?
    //from given store stock, user puts the product that he chose (by its product number) in the cart

    public ShoppingCart putProductInCart(Store store,Product chosenProduct) {
        //when the product is already in Cart, we need fewer checks
        boolean isAlreadyInCart=false;
        // running on the products in Cart
        for (Product product:this.productsInCart) {
            //if the chosen product is already in the Cart we just need to set its new amount in the Cart and in stock
         if (product.getProductNum()==chosenProduct.getProductNum()){
             product.setAmount(product.getAmount()+chosenProduct.getAmount());
          isAlreadyInCart=true;
          break;
         }
        }
        // running on store's list of products
        for (Product product: store.productsInStore) {
            //when a chosen productNumber by a customer, is founded in the stock list
            if (product.getProductNum() == chosenProduct.getProductNum()){
            // reduce the amount of this product from the store when the user adding it to the Cart
            product.setAmount(product.getAmount()-chosenProduct.getAmount());
            //do not need the other operations when its already in Cart
            if (isAlreadyInCart){
                return this;
            }
//            // reset the flag
//            isAlreadyInCart=false;

            //set the chosen product's name and price in the Cart
            chosenProduct.setProductName(product.getProductName());
            chosenProduct.setPrice(product.getPrice());
            //add the chosen product to the Cart
            this.productsInCart.add(chosenProduct);
            //update the total cost of the cart by the chosen product price
            this.setCartTotalCost(chosenProduct.getPrice());
                //remove a product from stock when the amount of product in the store is 0 after adding it to the user's Cart
                if (product.getAmount()==0) {
                    store.productsInStore.remove(product);
                }

        }
        }
        return this;
    }


    //////////////////////////////////////////////////////////////
    //Getters and Setters
    public int getCartTotalCost() {
        return cartTotalCost;
    }

    public void setCartTotalCost(int cartTotalCost) {
        this.cartTotalCost = cartTotalCost;
    }


}


