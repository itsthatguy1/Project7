/**
 * Allows ATM to communicate with the bank.
 * Each Bank object represents a communication with the Bank, not the Bank itself.
 * Each method in the Bank class represents a different type of communication with the ATM. Each bank object will only ever have one of its methods called.
 * As a result, only one of the fields will ever be defined for a given bank object. The field which is defined represents the type of communication and its outcome.
 * @author Sean Stock
 * @version 2.27.18
 * @todo consider a more fitting name for the class
 */

public class Bank
{
    private boolean approved;
    private boolean hasDeposited;
    private boolean correctPIN;
    private boolean correctCardNum;

    /**
     * Constructor of class Bank.
     */
    public Bank()
    {
    }

    /**
     * Allows transactions to be approved.
     * @return approved
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
     * @return correctPIN
     */
    public boolean verifyPIN(int pin)
    {
        int PIN = pin;
        correctPIN = true;
        return correctPIN;
    }

    /**
     * Allow Reader object to verify a card number.
     * @return correctCardNum
     */
    public boolean verifyCard(long cardNum)
    {
        long cardNumber = cardNum;
        correctCardNum = true;
        return correctCardNum;
    }
}