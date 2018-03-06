/**
 * Allows user to insert a card to be read.
 * Allows user to input their PIN number.
 * A printer object represents a card reader and PIN pad.
 * @author Sean Stock
 * @version 2.27.18
 */

import java.util.Scanner;
import java.util.Random;

public class Reader
{
    private Scanner scanner;
    private Bank checkPIN;
    private Random rand;
    private boolean cardIn;
    private boolean correctPIN;
    private long cardNum;

    /**
     * Constructor of class Reader.
     */
    public Reader()
    {
        checkPIN = new Bank();
        scanner = new Scanner(System.in);
        cardIn = false;
        correctPIN = false;
        rand = new Random();
    }

    /**
     * Allows user to insert a card.
     * @return cardIn
     */
    public boolean insertCard()
    {
        System.out.println("Please insert your card (Type *Insert*)");
        String inserted = scanner.nextLine();
        if (inserted.equals("*Insert*"))
        {
            cardIn = true;
        }
        return cardIn;
    }

    /**
     * Reads the card that the user inserted. (As of right now it generates a random number as a placeholder).
     * @return cardNum
     */
    public long readCard()
    {
        final long  MIN_CARD_NUM = 1000000000000000L;
        final long MAX_CARD_NUM = 9999999999999999L;
        cardNum = MIN_CARD_NUM+((long)(rand.nextDouble()*(MIN_CARD_NUM - MAX_CARD_NUM)));
        return cardNum;
    }

    /**
     * Allows user to input a PIN number. Checks with bank to ensure that the PIN is correct.
     * @return correctPIN
     */
    public boolean inputPIN()
    {
        System.out.println("Please input your PIN");
        int pin = scanner.nextInt();
        correctPIN = checkPIN.verifyPIN(pin);
        return correctPIN;
    }

    /**
     * Asks user to remove their card.
     * Will loop until user removes their card
     * @return cardIn
     */
    public boolean removeCard()
    {
        System.out.println("Please remove your card (Type *Remove*)");
        String inserted = scanner.next();
        if (inserted.equals("*Remove*"))
        {
            cardIn = false;
        }
        else
        {
            removeCard();
        }
        return cardIn;
    }

    /**
     * @return cardIn
     */
    public boolean getCardIn()
    {
        return cardIn;
    }
}