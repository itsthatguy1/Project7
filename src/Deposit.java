/**
 * Allows user to deposit money from a check into their account.
 * A Deposit object represents a deposit slip.
 * @author Sean Stock
 * @version 2.27.18
 */

import java.util.Scanner;

public class Deposit
{
    private Bank envelopeConfirmation;
    private Scanner scanner;
    private int amountCash;
    private boolean hasDeposited;

    /**
     * Constructor of class Deposit.
     */
    public Deposit(int amountCash)
    {
        scanner = new Scanner(System.in);
        envelopeConfirmation = new Bank();
        this.amountCash = amountCash;
        hasDeposited = false;
    }

    /**
     * Prints message confirming successful deposit.
     */
    public void depositMessage()
    {
        System.out.println("Successfully deposited $" + amountCash + " to some account");
        System.out.println("Thank you.");
    }

    /**
     * Allows user to insert envelope into deposit slip.
     * Will ensure that user has inserted check, and will send confirmation to the bank.
     * @return hasDeposited
     * @todo make it so that if user does not deposit slip first time, they are rejected and transaction is cancelled on the spot.
     */
    public boolean checkSlip()
    {
        System.out.println("Please Deposit the envelope. (Type *Deposit*)");
        String slip = scanner.nextLine();
        if (slip.equals("*Deposit*"))
        {
            hasDeposited = true;
            envelopeConfirmation.hasDeposited();
        }
        else
        {
            checkSlip();
        }
        return hasDeposited;
    }
}