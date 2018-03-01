/**
 * Allows ATM to communicate with the bank.
 * Each Bank object represents a communication with the Bank, not the Bank itself.
 * Each method in the Bank class represents a different type of communication with the ATM. Each bank object will only ever have one of its methods called.
 * As a result, only one of the fields will ever be defined for a given bank object. The field which is defined represents the type of communication and its outcome.
 * @author Sean Stock
 * @version 2.27.18
 */

public class Bank
{
    boolean approved;
    boolean hasDeposited;
    boolean correctPIN;

    /**
     * Constructor of class Bank.
     */
    public Bank()
    {
    }

    /**
     * Allows transactions to be approved.
     */
    public boolean getApproval()
    {
        approved = true;
        return approved;
    }

    /**
     * Lets Deposit object communicate that a check has been inserted.
     */
    public void hasDeposited()
    {
        hasDeposited = true;
    }

    /**
     * Allows Reader object to verify a PIN number.
     */
    public boolean verifyPIN(int pin)
    {
        int PIN = pin;
        correctPIN = true;
        return correctPIN;
    }
}
