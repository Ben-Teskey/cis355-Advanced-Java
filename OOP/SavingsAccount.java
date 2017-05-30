/**
 * The following class is a subclass of account, with the unique specification that it must not
 * allow any overdraws. Since the account class already throws an exception when any overdraft attempt
 * is made, we do not need to provide any further implementation. It is inherited.
 */
public class SavingsAccount extends Account {

    SavingsAccount() {

        super();

    }

    /**
     * Arg constructor with the following parameters:
     *
     * @param id an integer for account id
     * @param balance a double for the account's balance
     */
    SavingsAccount(int id, double balance) {

        super(id, balance);

    }

}
