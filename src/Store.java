import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Store {
    //    private Product product;
    protected List<User> users;
    //todo:check if protected problematic
    protected List<Product> productsInStore;

    protected LinkedList<Customer> customers;
    protected  List<Worker>workers;


    public Store() {
        //todo: decide if to choose this or ArrayList
        this.users = new LinkedList<>();
        this.productsInStore = new LinkedList<>();
        this.customers = new LinkedList<>();
    }

    //Methods

    //create a new User
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        int getType;
        //todo: ask Asaf how to change it to polymorphism
        String userType = null;
        System.out.println("Which account would you like to create?");
        System.out.println("1- Worker account");
        System.out.println("2- Customer account?");
        //update the userType of this new Account
        getType = scanner.nextInt();
        if (getType == 2) {
            userType = "Customer";
//            //add to Customers list
//            Customer newCustomer=new Customer();
//            customers.add(newCustomer);
        } else {
            userType = "Worker";
        }
        String firstName = null;
        String lastname = null;
        Rank rank = null;
        //get isVip?  answer from the user
        int vipAnswer;
        // by default the user isn't Vip member
        boolean isVIP = false;
        //by default customer isn't also worker unless its says so.
        int alsoWorker = 0;

        do {
            System.out.println("Enter your first name: ");
            firstName = scanner.next();
            //keep asking for first name as long as it contains numbers
        } while (!isNotContainNumbers(firstName));
        do {
            System.out.println("Enter you last name: ");
            lastname = scanner.next();
        } while (!isNotContainNumbers(lastname));
        String username = null;
        do {
            System.out.println("Enter username: ");
            username = scanner.next();
        } while (this.isUserNameExists(username));
        System.out.println("Your username is valid!\n");
        String password = null;
        do {
            System.out.println("Enter your password: ");
            password = scanner.next();
            if (password.length() < 6) {
                System.out.println("Your password is too short.");
            }
        } while (password.length() < 6);
        System.out.println("Your password is valid!\n");
        //todo:Check why isVIP not represented in the newUser object
        //if the user is a customer
        if (getType == 2) {
            System.out.println("Are you a VIP member?");
            System.out.println("1-Yes");
            System.out.println("2-No");
            vipAnswer = scanner.nextInt();
            //if the user is VIP
            if (vipAnswer == 1) {
                System.out.println("welcome to the VIP!");
                isVIP = true;
            }
            //check if customer is also a worker
            System.out.println("Are you also a Worker?");
            System.out.println("1-Yes");
            System.out.println("2-No");
            alsoWorker = scanner.nextInt();
        }
        //if the user is a worker or a costumer and also worker
        if (getType == 1 || alsoWorker == 1) {
            System.out.println("What is your Rank?");
            System.out.println("0-Regular \n1-Manager\n2-In management team");
            //get Enum options from  the user
            rank = Rank.values()[scanner.nextInt()];
            switch (rank) {
                case REGULAR:
                    System.out.println("regular worker");
                    //todo:update currentUser object
                    break;
                case MANAGER:
                    System.out.println("manager");

                    //todo:update currentUser object
                    break;
                case IN_MANAGEMENT_TEAM:
                    System.out.println("in management team");
                    //todo:update currentUser object
                    break;
            }
        }

        User newUser = new User(firstName, lastname, isVIP, rank, username, password, userType);
        //adding newUser to the LinkedList
        this.users.add(newUser);
        System.out.println("New User was added!\n");
        //test if the values are inserted to the newUser object
        System.out.println(newUser.toString());
    }


    //checks if the userName of a new account is already exist and cannot be used again
    private boolean isUserNameExists(String username) {
        boolean exists = false;
        for (User currentUser : this.users) {
            if (currentUser != null) {
                if (currentUser.getUsername().equals(username)) {
                    exists = true;
                    System.out.println("This username is already exists,Try a new one");
                    break;
                }
            }
        }
        return exists;
    }


    //checks if the firstname and lastname strings dose not contains numbers (returns "true" if they are valid names)
    //todo: check if i need to change to isContainNumbers
    private boolean isNotContainNumbers(String name) {
        boolean notContainNum = true;
        if (name.matches(".*\\d.*")) {
            //there is a number in the name
            notContainNum = false;
            System.out.println("You can't enter numbers");
            return notContainNum;
        }
        return notContainNum;
    }


    //todo: check if i will need to return User or void, and if this func should not be in User class
    //log in the system if the account is exists
    public User login() {
        User found = new User();
        Scanner scanner = new Scanner(System.in);
        int getType;
        System.out.println("Which account would you like to Log-In?");
        System.out.println("1-Worker account");
        System.out.println("2-Customer account");
        getType = scanner.nextInt();
        // by default the UserType is Customer
        String userType = "Customer";

        if (getType == 1) {
            //update the type of the chosen account for login()
            userType = "Worker";
            System.out.println("Hello Worker!\n");
        } else {
            System.out.println("Hello Customer!\n");
        }
        System.out.println("Enter username: ");
        String username = scanner.next();

        System.out.println("Enter password");
        String password = scanner.next();

        // check in our UsersList if the username and password are exists
        for (User currentUser : this.users) {
            //check if the userType is the same
            if ((currentUser.getUserType().equals(userType))) {
                //check if the username and password are the same
                if (currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password)) {
                    found = currentUser;
                    if (found.getUserType().equals("Customer")) {
                        customerLoggedIn(found);
                    } else if (found.getUserType().equals("Worker")) {
                        workerLoggedIn(found);
                    }
                    break;
                } else {
                    System.out.println("Wrong credentials!\n");
                    break;
                }
            }
            //todo:bug here when i enter first worker account then this line down here show
            //when the userType is not the as our UsersType List print an Error message
            System.out.println("Error! cannot find your account in our " + userType + " accounts list\n");
        }
//        customerLoggedIn(found);
        return found;
    }


    //get the products that the user chose
    public Product getUserProductChoice() {
        Scanner scanner = new Scanner(System.in);
        int getProductNum, getAmount;
        System.out.println("\nEnter the product's number that would you like to purchase:");
        System.out.println("Or Press -1 for finish your shopping");
        getProductNum = scanner.nextInt();
        Product userProduct = new Product();
        //user choice is to quit
        if (getProductNum == -1) {
            System.out.println("You chose to finish your shopping. Have a nice day!");
            return null;
        }
        //todo:check if this works
        //get the amount of product that a user wants, and make sure that the amount is not negative or zero
        do {
            System.out.println("How many items from this product do you want to put in your shopping cart?");
            getAmount = scanner.nextInt();

            if (getAmount <= 0) {
                System.out.println("Error! you cant enter negative amount of product.");
            }
        } while (getAmount <= 0);
        //set the product info by user's selection
        userProduct.setProductNum(getProductNum);
        userProduct.setAmount(getAmount);
        return userProduct;
    }

    //returns if a user's chosen product is in stock (choosing by productNumber and amount)
    public boolean isProductInStock(Store productsInStore, Product userProduct) {
        //by default i didn't found the product.
        boolean foundProduct = false;
        Scanner scanner = new Scanner(System.in);
        //running on Store's product list and checks if the product number from the user input exists in the stock list
        for (Product productInStock : this.productsInStore) {
            //when a chosen productNumber by a customer, is founded in the stock list and its amount in the stock is > 0
            if ((productInStock.getProductNum() == userProduct.getProductNum()) && productInStock.getAmount() > 0) {
                //when there is not enough from existing product's amount in the stock
                if (userProduct.getAmount() > productInStock.getAmount()) {
                    System.out.println("We are sorry, there are just " + productInStock.getAmount() + " of " +
                            productInStock.getProductName() + " in stock.");
                    System.out
                            .println("Would you like to add to your Cart these " + productInStock.getAmount() + " " +
                                    productInStock.getProductName() + "?");
                    System.out.println("1:Yes\n-1:No");
                    int notEnough = scanner.nextInt();

                    //when the user chose not to buy this amount
                    if (notEnough == -1) {
                        return false;
                        //when the user choose to buy the last products in stock
                    } else {
                        userProduct.setAmount(productInStock.getAmount());
                    }
                }
                foundProduct = true;
                break;

            }
        }
        //when we scan the whole products in the store and did not find a match productNumber
        if (!foundProduct) {
            System.out.println("This Product Number does not exist in our store.");
        }
        return foundProduct;
    }


    //finishing the purchase.
    public boolean finishPurchase(ShoppingCart currentCart, User user) {
        boolean finishPurchase = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("For finish shopping Press -1\nTo Continue Shopping Press 1");
        int userDecision = scanner.nextInt();
        //print the total cost and clear the Cart
        if (userDecision == -1) {
            currentCart.printTotalCostCart(currentCart);
            System.out.println("Thank you for shopping in our store. Have a nice day!\n");
            //update totalCost if it is a customer
            if (user.getUserType().equals("Customer")) {
                Customer customer = new Customer();
                //update  that this user is a customer
                customer.setUsername(user.getUsername());
                customer.setLastName(user.getLastName());
                customer.setVIP(user.isVIP());
                customer.setCostOfPurchases(currentCart.getCartTotalCost());
                //customer has purchased?
                if (customer.getCostOfPurchases() > 0) {
                    customer.setHasPurchased(true);
                }
                //adding to the customers list
                customers.add(customer);
            }
            //clear the shopping cart
            currentCart.productsInCart.clear();
            finishPurchase = true;
        }
        return finishPurchase;
    }

    public void makePurchase(User user){
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        int getProductNum, getAmount;
        do {

            //print to the user the list of the products that are in stock in the store
            user.printProductsInStore(this);
            // if the product is in stock, the user can proceed purchasing
            Product userProduct = getUserProductChoice();
            if (isProductInStock(this, userProduct)) {
                //The user put in his Cart the products that he choose
                shoppingCart = shoppingCart.putProductInCart(this, userProduct);
                //print all  the products in the user's Cart
                shoppingCart.printCartProducts(shoppingCart);
                if (user.getUserType().equals("Worker")){
                }
                //Print the total cost of current user's Shopping Cart
                shoppingCart.printTotalCostCart(shoppingCart);
            }
//            else {
//                //when the product isn't in stock finish the purchase
//                finishPurchase(shoppingCart);
//            }
        } while (!finishPurchase(shoppingCart, user));
    }


    //todo: not sure of ShoppingCart type
    //When a Customer type of user is log in
    public void customerLoggedIn(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        int getProductNum, getAmount;

        // after successful login, find if its an VIP customer account
        if (user.getUserType().equals("Customer") && user.isVIP()) {
            System.out.println("Hello " + user.getFirstName() + " " + user.getLastName() + " VIP!");
        }
        //customer account but not VIP
        else if (user.getUserType().equals("Customer")) {
            System.out.println("Hello " + user.getFirstName() + " " + user.getLastName() + "!");
        }

        do {

            //print to the user the list of the products that are in stock in the store
            user.printProductsInStore(this);
            // if the product is in stock, the user can proceed purchasing
            Product userProduct = getUserProductChoice();
            if (isProductInStock(this, userProduct)) {
                //The user put in his Cart the products that he choose
                shoppingCart = shoppingCart.putProductInCart(this, userProduct);
                //print all  the products in the user's Cart
                shoppingCart.printCartProducts(shoppingCart);
                //Print the total cost of current user's Shopping Cart
                shoppingCart.printTotalCostCart(shoppingCart);
            }
//            else {
//                //when the product isn't in stock finish the purchase
//                finishPurchase(shoppingCart);
//            }
        } while (!finishPurchase(shoppingCart, user));
        //todo: decide what to do when !isProductInStock(this)
//        //when the user chose a product that is not in stock
//        if (!isProductInStock(this)) {
//            System.out.println("Error! we cant find this product number. Please try enter it again");
//        }

    }

    public void workerLoggedIn(User user) {
        Scanner scanner = new Scanner(System.in);
        int workerChoice;

        // after successful login, print the worker stats
        if (user.getUserType().equals("Worker")) {
            Worker newWorker=new Worker();
            newWorker.setFirstName(user.getFirstName());
            newWorker.setLastName(user.getLastName());
            newWorker.setRank(user.getRank());
            workers.add(newWorker);
            System.out.println("Hello " + newWorker.getFirstName() + " " + newWorker.getLastName() + " " + newWorker.getRank() + " !");
        }

        do {
            System.out.println("1-print the whole Customers list");
            System.out.println("2-print only the VIP's Customers");
            System.out.println("3-print the Customers who made one purchase at least");
            System.out.println("4-Print the Customer whose purchase amount is the highest ");
            System.out.println("5-Enter a new product to the store");
            System.out.println("6-Change product's status");
            System.out.println("7-make a purchase");
            System.out.println("8-Exit");

            workerChoice = scanner.nextInt();


            switch (workerChoice) {
                case 1:
                    user.printAllCustomers(this);
                    break;
                case 2:
                    user.printVipCustomers(this);
                    break;
                case 3:
                    user.printMadePurchase(this);
                    break;
                case 4:
                    user.printTopCustomer(this);

                case 5:
                    user.enterProductsToStock(this);
                case 6:
                    user.changeProductStatus(this);
                case 7:user.makePurchase();


            }
        } while (workerChoice != 8);
    }


}


