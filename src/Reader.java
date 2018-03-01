/**
 * Allows user to insert a card to be read.
 * Allows user to input their PIN number.
 * A printer object represents a card reader and PIN pad.
 * @author Sean Stock
 * @version 2.27.18
 * @todo generate some random card number to save/send to bank and allow bank to verify that card number
 */

import java.util.Scanner;

public class Reader
{
    private Scanner scanner;
    private Bank checkPIN;
    private boolean hasInserted;
    public boolean correctPIN;

    /**
     * Constructor of class Reader.
     */
    public Reader()
    {
        checkPIN = new Bank();
        scanner = new Scanner(System.in);
        hasInserted = false;
        correctPIN = false;
    }

    /**
     * Allows user to insert a card.
     */
    public boolean insertCard()
    {
        System.out.println("Please insert your card (Type *Insert*)");
        String inserted = scanner.nextLine();
        if (inserted.equals("*Insert*"))
        {
            hasInserted = true;
        }
        return hasInserted;
    }

    /**
     * Allows user to input a PIN number. Checks with bank to ensure that the PIN is correct.
     */
    public boolean inputPIN()
    {
        System.out.println("Please input your PIN");
        int pin = scanner.nextInt();
        correctPIN = checkPIN.verifyPIN(pin);
        return correctPIN;
    }
}
