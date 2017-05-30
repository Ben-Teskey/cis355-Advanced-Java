/**
 * The following class is a subclass of account, with the unique specification that it must allow
 * specification of an overdraw limit. Because the account class's withdraw method does not allow for overdrafts,
 * we must override this method, as well as create a unique overdrawLimit data member.
 */
public class CheckingAccount extends Account{

    private int overdrawLimit;

    CheckingAccount() {

        super();

    }

    /**
     * Arg constructor with the following parameters:
     *
     * @param id an integer for account id
     * @param balance a double for the account's balance
     */
    CheckingAccount(int id, double balance) {

        super(id, balance);

    }

    public void setOverdrawLimit(int overdrawLimit) {

        this.overdrawLimit = overdrawLimit;

    }

    public int getOverdrawLimit() {

        return overdrawLimit;

    }

    /**
     * Override withdraw method to use specific overdraw limit instead of default, which is no overdraw
     *
     * @param amount double variable for amount to withdraw
     */
    @Override
    public void withdraw(double amount) throws InsufficientAmount {

        if (getBalance() < amount - overdrawLimit) throw new InsufficientAmount();
        else setBalance(getBalance() - amount);

    }

}
