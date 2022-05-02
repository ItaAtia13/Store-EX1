import java.util.Scanner;

public class User  {
    private String firstName;
    private String lastName;
    private  boolean isVIP;
    public Rank rank;
    private String username;
    private String password;
    private String  userType;


    //todo:not sure if user should get field like isVip and rank
    //CONSTRUCTORS
    public User(){};

    public User(String firstName, String lastName,boolean isVIP, Rank rank, String username, String password,String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isVIP=isVIP;
        this.rank = rank;
        this.username = username;
        this.password = password;
        this.userType=userType;
    }






    //Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Rank getRank() {
        return rank;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    //Methods

    //enter products to the store
    //todo:does this func should be Store or List<Product>?
    public void enterProductsToStock(Store store) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many different products would you like to enter?");
        int numOfProducts = scanner.nextInt();
        for (int i = 0; i < numOfProducts; i++) {
            Product currentProduct = new Product();
            //product name
            System.out.println("Enter product name:");
            //get the name of the product from the user input
            String productName = scanner.next();
            currentProduct.setProductName(productName);
            //product number
            System.out.println("Enter product number:");
            //get the number of the product from the user input
            int productNum = scanner.nextInt();
            currentProduct.setProductNum(productNum);
            //price
            System.out.println("Enter product price:");
            //get the price of the product from the user input
            int productPrice = scanner.nextInt();
            currentProduct.setPrice(productPrice);
            //amount from this current product
            System.out.println("Enter the amount from this product :");
            //get the amount from this current product from the user
            int amountOfProduct = scanner.nextInt();
            currentProduct.setAmount(amountOfProduct);
            //discount percentages for Vip
            System.out.println("Enter the amount of discount percentages for VIP customers to this product :");
            int discountPrecent=scanner.nextInt();
            currentProduct.setDiscountPrecent(discountPrecent);

            //make sure that the user will add positive amount of products to the store
            if (amountOfProduct > 0) {
                //add currentProduct to product list
                store.productsInStore.add(currentProduct);
            } else {
                System.out.println("Error! You must add positive amount of products to the store");
            }
            //is in stock
            currentProduct.setInStock(true);
        }
        //test
        System.out.println("|product name| |product number| |product price| |amount|");
        for (Product product : store.productsInStore) {
            System.out.println(
                    product.getProductName() + "   " + product.getProductNum() + "   " + product.getPrice() + "   " +
                            product.getAmount());
        }

    }

    public void printAllCustomers(Store store){
        //flag is down when didnt found a Customer in the list
        int flag=0;
        System.out.println("These are the customers in our store:\n");
        for (User user: store.users) {
//            if (store.users.isEmpty())
            if (user.getUserType().equals("Customer")){
                flag=1;
                System.out.println(user.getFirstName()+" "+user.getLastName()+"\n");
            }
        }
        if (flag==0){
            System.out.println("The Customers list is empty");
        }
        //reset flag
        flag=0;
    }

    //print the VIP'S Customers
    public void printVipCustomers(Store store){
        //flag is down when didnt found a Customer in the list
        int flag=0;
        System.out.println("These are the VIP customers in our store:\n");
        for (User user: store.users) {
            if (user.getUserType().equals("Customer")&&user.isVIP){
                flag=1;
                System.out.println(user.getFirstName()+" "+user.getLastName()+"\n");
            }
        }
        if (flag==0){
            System.out.println("The Customers VIP'S list is empty");
        }
        //reset flag
        flag=0;
    }

    // print all the customers that has  made at least single purchase
    public void printMadePurchase(Store store){
        System.out.println("This is the list of all the Customers that has made at least one single purchase:\n");
        for (Customer customer: store.customers) {
            if (customer.isMadePurchase()){
                System.out.println(customer.getFirstName()+" "+customer.getLastName()+"\n");
            }
        }
    }

    //Top customer
    public void printTopCustomer(Store store){
        Customer topCustomer = new Customer();
        for (Customer customer: store.customers) {
            if (customer.getCostOfPurchases()>topCustomer.getCostOfPurchases()){
                topCustomer=customer;
            }
        }
        System.out.println("The customer whose purchase amount is the highest is :"+topCustomer.getFirstName()+" "+topCustomer.getLastName()+" the purchase amount: "+topCustomer.getCostOfPurchases());


    }
    //print all the available products names and numbers in the store
    public void printProductsInStore(Store store) {
        int i = 1;
        System.out.println("These are the available products in the Store:");
        for (Product product : store.productsInStore) {
            //for edge case when the product is out of stock
            if (product.getAmount() > 0) {
                System.out.println(
                        i + ". " + product.getProductName() + ", product number: " + product.getProductNum() +
                                ", price: " + product.getPrice() + "₪, amount in stock: " + product.getAmount());
                i++;
            }
        }
    }

    public void changeProductStatus(Store store){
        //flag when a product num isn't valid
        int flag=0;
        Scanner scanner=new Scanner(System.in);
        printProductsInStore(store);
        System.out.println("Enter a product number for changing its Status in the stock: ");
        int productNumberChoice= scanner.nextInt();
        System.out.println("How would you like to change this product's status?\n1:Set it's amount in stock\n-1:Make it out of stock");
        int stockChoice=scanner.nextInt();
        for (Product product:store.productsInStore) {
            //when the user chose to make a product out of stock
            if (stockChoice==-1 && product.getProductNum()==productNumberChoice){
                flag=1;
                product.setAmount(0);
                product.setInStock(false);
                System.out.println(product.getProductName()+" is now out of stock!");
                //delete this out of stock product from the list
                store.productsInStore.remove(product);
            } else if (stockChoice==1 && product.getProductNum()==productNumberChoice) {
                flag=1;
                System.out.println("Please set "+product.getProductName()+" amount: ");
                int newAmount=scanner.nextInt();
                product.setAmount(newAmount);
                product.setInStock(true);
                System.out.println("We have update the new amount of this product in stock");

            }

        }
        //when the chosen product number is not valid
        if (flag!=1){
            System.out.println("Error! you have entered wrong product number");
        }

    }

    //todo: change it?
    //by each worker's rank' get the discount and reduce in the end of the purchase
    public void rankDiscount(Customer customer){
        switch (customer.getRank()){
            // REGULAR CUSTOMERS DOSENT HAVE DISCOUNT
            case CUSTOMER:
                customer.setDiscount(0);
                break;

            case  REGULAR:
                //get the discount value
                customer.setDiscount(0.1);
                //todo this line should be out of this func
                //reduce it from the total sum
//                worker.setCostOfPurchases((worker.getCostOfPurchases()- worker.getDiscount()));
                break;
            case MANAGER:
                customer.setDiscount(0.2);
//                worker.setCostOfPurchases((worker.getCostOfPurchases()- worker.getDiscount()));

                break;
            case IN_MANAGEMENT_TEAM:
                customer.setDiscount(0.3);
//                worker.setCostOfPurchases((worker.getCostOfPurchases()- worker.getDiscount()));

                break;
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isVIP=" + isVIP +
                ", rank=" + rank +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}


