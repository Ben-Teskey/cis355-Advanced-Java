import java.text.DecimalFormat;
import java.util.Date;

/**
 * The following class stores all data and performs all operations of a generic bank account as specified
 * in section 9.7.
 */
public class Account {

    // Data members
    private int id;
    private double balance;
    private static double annualInterestRate; // Static so that all accounts have the same interest rate
    private Date dateCreated;

    /*
     * The default for most data members is 0. The default for date of course is the current date,
     * stored using the no arg constructor of the date util class.
     */
    Account() {

        id = 0;
        balance = 0;
        annualInterestRate = 0;
        dateCreated = new Date();

    }

    /**
     * Arg constructor with the following parameters:
     *
     * @param id an integer for account id
     * @param balance a double for the account's balance
     */
    Account(int id, double balance) {

        this.id = id;
        this.balance = balance;
        annualInterestRate = 0;
        dateCreated = new Date();

    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public double getBalance() {

        return balance;

    }

    public void setBalance(double balance) {

        this.balance = balance;

    }

    public static double getAnnualInterestRate() {

        return annualInterestRate;

    }

    public static void setAnnualInterestRate(double annualInterestRate) {

        Account.annualInterestRate = annualInterestRate;

    }

    public Date getDateCreated() {

        return dateCreated;

    }

    public double getMonthlyInterestRate() {

        return annualInterestRate / 12;

    }

    public double getMonthlyInterest() {

        return ((annualInterestRate / 12) / 100) * balance;

    }

    /**
     * The following method withdraws a specified amount of money from the account with:
     *
     * @param amount a double of the amount to withdraw
     */
    public void withdraw(double amount) throws InsufficientAmount {

        if (balance < amount) throw new InsufficientAmount();
        else balance = balance - amount;

    }

    /**
     * The following method deposits a specified amount of money to the account with:
     *
     * @param amount a double of the amount to deposit
     */
    public void deposit(double amount) {

        balance = balance + amount;

    }

    /**
     * The following method overrides the toString method in the object class,
     * printing the id, date, balance, and monthly interest rate
     *
     * @return a String of id, date, balance, monthly interest rate
     */
    @Override
    public String toString() {

        DecimalFormat balanceFormat = new DecimalFormat("###,###,###.00");
        DecimalFormat monthlyInterestFormat = new DecimalFormat("#.00");

        return "id: " + id + " date: " + dateCreated + " balance: $" + balanceFormat.format(balance)
                + " monthly interest rate: " + monthlyInterestFormat.format(getMonthlyInterestRate());

    }

}
