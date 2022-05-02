import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Store {
    //    private Product product;
    protected LinkedList<User> users;
    //todo:check if protected problematic
    protected LinkedList<Product> productsInStore;

    protected LinkedList<Customer> customers;
    protected LinkedList<Worker> workers;


    public Store() {
        //todo: decide if to choose this or ArrayList
        this.users = new LinkedList<>();
        this.productsInStore = new LinkedList<>();
        this.customers = new LinkedList<>();
        this.workers = new LinkedList<>();
    }

    //Methods

    //create a new User
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        int getType;
        String userType;
        System.out.println("Which account would you like to create?");
        System.out.println("1- Worker account");
        System.out.println("2- Customer account?");
        //update the userType of this new Account
        getType = scanner.nextInt();
        if (getType == 2) {
            userType = "Customer";
        } else {
            userType = "Worker";
        }
        String firstName;
        String lastname;
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
        } while (isContainNumbers(firstName));
        do {
            System.out.println("Enter you last name: ");
            lastname = scanner.next();
        } while (isContainNumbers(lastname));
        String username;
        do {
            System.out.println("Enter username: ");
            username = scanner.next();
        } while (this.isUserNameExists(username));
        System.out.println("Your username is valid!\n");
        String password;
        do {
            System.out.println("Enter your password: ");
            password = scanner.next();
            if (password.length() < 6) {
                System.out.println("Your password is too short.");
            }
        } while (password.length() < 6);
        System.out.println("Your password is valid!\n");
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
        //When the user is a customer without any rank
        if (alsoWorker == 2) {
            rank = Rank.CUSTOMER;
        }
        //if the user is a worker or a costumer and also worker
        if (getType == 1 || alsoWorker == 1) {
            System.out.println("What is your Rank?");
            System.out.println("1-Regular \n2-Manager\n3-In management team");
            //get Enum options from  the user
            rank = Rank.values()[scanner.nextInt()];
            switch (rank) {
                case REGULAR:
                    System.out.println("regular worker");
                    break;

                case MANAGER:
                    System.out.println("manager");

                    break;
                case IN_MANAGEMENT_TEAM:
                    System.out.println("in management team");

                    break;
            }
        }

        User newUser = new User(firstName, lastname, isVIP, rank, username, password, userType);
        //adding newUser to the LinkedList
        this.users.add(newUser);
        System.out.println("New User was added!\n");
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
    private boolean isContainNumbers(String name) {
        if (name.matches(".*\\d.*")) {
            //there is a number in the name
            System.out.println("You can't enter numbers");
            return true;
        }
        return false;
    }


    //log in the system if the account is exists
    public void login() {
        User found;
        Scanner scanner = new Scanner(System.in);
        int getType;
        int flag = 0;
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
            //check if the username and password are the same
            if (currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password)) {
                flag = 1;
                //when the userType is not the as our UsersType List, but thw username and password are valid print an Error message
                if (!currentUser.getUserType().equals(userType)) {
                    System.out.println("Error! cannot find your account in our " + userType + "s accounts list\n");
                    break;
                }
                found = currentUser;
                if (found.getUserType().equals("Customer")) {
                    Customer newCustomer = new Customer();
                    newCustomer.setFirstName(currentUser.getFirstName());
                    newCustomer.setLastName(currentUser.getLastName());
                    //todo:check if need eventually rank in customer
                    newCustomer.setRank(currentUser.getRank());
                    customers.add(newCustomer);
                    customerLoggedIn(newCustomer);
                } else if (found.getUserType().equals("Worker")) {
                    Worker newWorker = new Worker();
                    newWorker.setFirstName(currentUser.getFirstName());
                    newWorker.setLastName(currentUser.getLastName());
                    newWorker.setRank(currentUser.getRank());
                    workers.add(newWorker);
                    workerLoggedIn(newWorker);
                }
            }
        }
        //when username or password are invalid
        if (flag == 0) {
            System.out.println("Wrong credentials!\n");
        }
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
            return null;
        }
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
    public boolean isProductInStock(Product userProduct) {
        //by default, I didn't found the product.
        boolean foundProduct = false;
        int flag = 0;
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
            flag = 1;
        }
        //when we scan the whole products in the store and did not find a match productNumber
        if (!foundProduct && flag == 1) {
            System.out.println("This Product Number does not exist in our store.");
        }
        return foundProduct;
    }


    //finishing the purchase.
    public boolean finishPurchase(ShoppingCart currentCart, Customer customer) {
        boolean finishPurchase = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Are you sure that you want to finish your shopping?\nYes press -1\nTo Continue Shopping Press 1");
        int userDecision = scanner.nextInt();
        //print the total cost and clear the Cart
        if (userDecision == -1) {
            currentCart.printTotalCostCart(currentCart, customer);
            System.out.println("Thank you for shopping in our store. Have a nice day!\n");
            //clear the shopping cart
            currentCart.productsInCart.clear();
            finishPurchase = true;
        }
        return finishPurchase;
    }

    public void makePurchase(Customer customer) {
        ShoppingCart shoppingCart = new ShoppingCart();
        do {

            //print to the user the list of the products that are in stock in the store
            customer.printProductsInStore(this);
            // if the product is in stock, the user can proceed purchasing
            Product userProduct = getUserProductChoice();
            if (isProductInStock(userProduct)) {
                //The user put in his Cart the products that he choose
                shoppingCart = shoppingCart.putProductInCart(this, userProduct);
                //print all  the products in the user's Cart
                shoppingCart.printCartProducts(shoppingCart);
                //Print the total cost of current user's Shopping Cart
                shoppingCart.printTotalCostCart(shoppingCart, customer);
                //todo: not sure if it needs to be here or in the end of printTotalCost
                //update that the customer has finally made a purchase with the date of purchase
                customer.setHasPurchased(true);
                customer.setDate(new Date());
                System.out.println("The Date of purchase of " + customer.getFirstName() + " " + customer.getLastName() +
                        " in the store is: " + customer.getDate());

            } else {
                System.out.println("Error! we cant find this product number. Please try enter it again");
            }

        } while (!finishPurchase(shoppingCart, customer));
    }


    //todo: not sure of ShoppingCart type
    //When a Customer type of user is log in
    public void customerLoggedIn(Customer customer) {
        // after successful login, find if it is an VIP customer account
        if (customer.isVIP()) {
            System.out.println("Hello " + customer.getFirstName() + " " + customer.getLastName() + " VIP!");
        }
        //customer account but not VIP
        else {
            System.out.println("Hello " + customer.getFirstName() + " " + customer.getLastName() + "!");
        }
        makePurchase(customer);
    }

    public void workerLoggedIn(Worker worker) {
        Scanner scanner = new Scanner(System.in);
        int workerChoice;

        // after successful login, print the worker stats
        System.out.println(
                "Hello " + worker.getFirstName() + " " + worker.getLastName() + " " + worker.getRank() + " !");


        do {
            System.out.println("\n1-print the whole Customers list");
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
                    worker.printAllCustomers(this);
                    break;
                case 2:
                    worker.printVipCustomers(this);
                    break;
                //todo:print when its empty
                case 3:
                    worker.printMadePurchase(this);
                    break;
                case 4:
                    worker.printTopCustomer(this);
                    break;

                case 5:
                    worker.enterProductsToStock(this);
                    break;
                case 6:
                    //todo: print a message when a product number is not valid
                    worker.changeProductStatus(this);
                    break;
                case 7:
                    makePurchase(worker);
                    break;
            }
        } while (workerChoice != 8);
    }


}


