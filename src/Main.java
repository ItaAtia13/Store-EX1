import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice, loginType;
        Store store = new Store();
        try {
            do {
                System.out.println("1-Create new account");
                System.out.println("2-Log in to your account");
                System.out.println("3-Exit");
                userChoice = scanner.nextInt();


                switch (userChoice) {
                    case 1:
                        store.createUser();
                        break;
                    case 2:
                        store.login();
                        break;
                    case 3:
                        break;

                }
            } while (userChoice != 3);
        } catch (Exception e) {
            System.out.println("Wrong input.");
        }
    }
}
