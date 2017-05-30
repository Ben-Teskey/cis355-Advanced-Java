import java.util.Scanner;

// Homework 3: ATM
// Student Name: Benjamin Teskey
// Course: CIS357, Winter 2017
// Instructor: Dr. Cho
// Date finished: 2/28/2017
// Program description: This program tests the core functionality of the Account class and both of its subclasses.
// These classes include all of the most important functionality of bank accounts.
public class ATMTeskey_HW3 {

    public static void main(String[] args) {

        System.out.println("Testing account class...");
        testAccount();

        System.out.println("Testing ATM...");
        testATM();

        System.out.println("Testing extra accounts...");
        testExtraAccounts();

    }

    /**
     * The following method tests the account class to make sure that it is working properly
     */
    public static void testAccount() {

        try {

            Account account1 = new Account(112, 20000);
            account1.setAnnualInterestRate(4.5);
            account1.withdraw(2500);
            account1.deposit(3000);

            Account account2 = new Account(112, 2000);
            account2.setAnnualInterestRate(4.5);
            account2.withdraw(2500);
            account2.deposit(3000);

            System.out.println(account1);
            System.out.println(account2);

        } catch (InsufficientAmount ex) {

            System.out.println("Insufficient fund!!!");

        } catch (Exception ex) {

            System.out.println("Exception thrown");

        }

    }

    /**
     * The following method tests the account class by simulating an ATM machine as specified in
     * section 10.7
     */
    public static void testATM() {

        // Declare and initialize variables
        Scanner sc = new Scanner(System.in);
        Account[] myAccounts = new Account[10];
        int idInput = 987;
        int menuInput = 364;

        for (int i = 0; i < myAccounts.length; i++) myAccounts[i] = new Account(i, 100);

        while (idInput > 9 || idInput < 0) {

            System.out.print("Enter an id: ");

            try {
                idInput = Integer.parseInt(sc.next());
            } catch (NumberFormatException ex) {
                System.out.println("Please try again. Data type must be integer.");
            }

        }

        System.out.println();

        while (menuInput != 4) {

            // Print menu
            System.out.println("Main menu");
            System.out.println("1: check balance");
            System.out.println("2: withdraw");
            System.out.println("3: deposit");
            System.out.println("4: exit");
            System.out.print("Enter a choice: ");

            try {
                menuInput = Integer.parseInt(sc.next());
            } catch (NumberFormatException ex) {
                System.out.println("Please try again. Data type must be integer.");
                menuInput = 12312;
            }

            // Call method to handle and process menu input
            handleInput(menuInput, idInput, myAccounts, sc);

        }

    }

    /**
     * The following method handles all possible menu inputs into the ATM machine and
     * processes whichever desired request is selected
     *
     * @param menuInput integer for user's selection
     * @param idInput integer for account id
     * @param myAccounts array of accounts including account accessed
     * @param sc scanner object used to read more user inputs
     */
    private static void handleInput(int menuInput, int idInput, Account[] myAccounts, Scanner sc) {

        if (menuInput == 1) System.out.println("The balance is " + myAccounts[idInput].getBalance());
        else if (menuInput == 2) {

            double withdrawInput = -34342;
            boolean insufficientAmount = false;

            while (withdrawInput < 0 || insufficientAmount) {

                System.out.print("Enter an amount to withdraw: ");

                try {

                    withdrawInput = Double.parseDouble(sc.next());

                    try {
                        myAccounts[idInput].withdraw(withdrawInput);
                        insufficientAmount = false;
                    } catch (InsufficientAmount ex) {
                        System.out.println("Insufficient fund!!!");
                        insufficientAmount = true;
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Please try again. Data type must be double.");
                }

            }

        } else if (menuInput == 3) {

            double depositInput = -123213;

            while (depositInput < 0) {

                System.out.print("Enter an amount to deposit: ");

                try {
                    depositInput = Double.parseDouble(sc.next());
                } catch (NumberFormatException ex) {
                    System.out.println("Please try again. Data type must be double.");
                }

            }

            myAccounts[idInput].deposit(depositInput);

        }

        System.out.println();

    }

    /**
     * The following method tests savings and checking accounts as specified in section 11.3
     */
    public static void testExtraAccounts() {

        SavingsAccount account1 = new SavingsAccount(1123,20000);
        account1.setAnnualInterestRate(5.5);
        CheckingAccount account2 = new CheckingAccount(1124,1000);
        account2.setAnnualInterestRate(5.0);
        account2.setOverdrawLimit(500);

        try {
            account2.withdraw(1100);
        }
        catch (InsufficientAmount ex) {
            System.out.println("Insufficient fund!!!");
        }

        account2.deposit(300);

        System.out.println(account1);
        System.out.println(account2);

    }

}
