/**
 * Represents the console of the ATM which controls each of the separate ATM parts to complete a transaction.
 * Each console object represents a transaction.
 * @author Sean Stock
 * @version 2.27.18
 * @todo CHECK COMMENTS
 * @todo Make an option to cancel during any point
 * @todo add gitignore
 * @todo INPUT SANITIZATION
 * @todo Create specific messages letting user know why they were rejected.
 * @todo look over variable names to ensure they still make any sense
 */

import java.util.Scanner;
import java.lang.Math;

public class Console
{
    private Scanner scanner;
    private Reader reader;
    private Bank approvalMessage;
    private int menuChoice;
    private int amountWithdrawn;
    private int amountDeposited;
    private boolean approved;
    private boolean userContinue;
    private boolean cardApproved;
    private long cardNum;

    /**
     * Main method in class Console
     * Useful for debugging/testing with just a single transaction
     * @param args
     */
    public static void main(String[] args)
    {
        boolean sameUser = false;
        long cardNum = 0;
        Console console = new Console(sameUser, cardNum);
    }

    /**
     * Constructor of class Console.
     */
    public Console(boolean sameUser, long cardNum)
    {
        amountDeposited = 0;
        amountWithdrawn = 0;
        scanner = new Scanner(System.in);
        reader = new Reader();
        approvalMessage = new Bank();
        if (sameUser)
        {
            menu();
            this.cardNum = cardNum;
        }
        if (!sameUser)
        {
            insertCard();
        }
    }

    /**
     * Lets the card reader accept a card. If a card is inserted, the checkPin method is run.
     */
    public void insertCard()
    {
        boolean hasInserted = reader.insertCard();
        if (hasInserted)
        {
            cardNum = reader.readCard();
            Bank checkCard = new Bank();
            if (checkCard.verifyCard(cardNum))
            {
                cardApproved = true;
                checkPIN();
            }
            else
            {
                cardApproved = false;
                refuseCard();
            }
        }
    }

    /**
     * Lets the reader accept a PIN input from the user.
     * If the reader determines that the PIN is incorrect, the user is prompted for their PIN.
     * If a user enters an incorrect PIN three times, the transaction is cancelled.
     * If the user inputs a correct PIN, the menu method is run.
     */
    public void checkPIN()
    {
        int tries = 1;
        final int MAX_TRIES = 3;
        boolean correctPIN = reader.inputPIN();
        while (!correctPIN && tries < MAX_TRIES)
        {
            System.out.println("I'm sorry, that is an incorrect PIN.");
            correctPIN = reader.inputPIN();
            tries++;
        }
        if (!correctPIN && tries >= MAX_TRIES)
        {
            System.out.println("I'm sorry, you have entered an incorrect PIN too many times.");
        }
        if (correctPIN)
        {
            menu();
        }
    }

    /**
     * First sets cardApproved to true, in the case of a user selecting to make another transaction.
     * Main menu of the ATM. Allows user to choose what type of transaction they would like.
     * Transaction types are Withdrawal, Deposit, Transfer, and Check Balance.
     * Accepts user input and runs appropriate method.
     */
    public void menu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1. Withdrawal");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        menuChoice = scanner.nextInt();
        while (menuChoice < 1 || menuChoice > 4)
        {
            System.out.println("I'm sorry, that is an invalid option. Please try again");
            menuChoice = scanner.nextInt();
        }
        switch (menuChoice)
        {
            case 1: withdrawal();
                    break;
            case 2: deposit();
                    break;
            case 3: transfer();
                    break;
            case 4: checkBalance();
                    break;
        }

    }

    /**
     * Method for performing a withdrawal.
     * Lets user input amount of cash they want.
     * Ensures that the user entered an amount that can be divided by 20.
     * Checks for bank verification. If transaction is rejected, printRejection method is called.
     * If transaction is accepted, a Dispenser object is created and instructed to dispense the appropriate amount of cash.
     * Afterward, the printReceipt method is called.
     */
    public void withdrawal()
    {
        final double MULTIPLE = 20;
        System.out.println("How much Cash would you like to withdraw?");
        int amountCash = scanner.nextInt();
        double checkCash = amountCash / MULTIPLE;
        while (checkCash-Math.floor(checkCash) != 0 || amountCash == 0)
        {
            System.out.println("You must enter a multiple of 20. Please try again.");
            amountCash = scanner.nextInt();
            checkCash = amountCash / MULTIPLE;
            System.out.println(checkCash);
        }
        approved = approvalMessage.getApproval();
        if (approved)
        {
            Dispenser dispenser = new Dispenser(amountCash);
            dispenser.dispenseCash();
            amountWithdrawn = amountCash;
            printReceipt(menuChoice);
        }
        else
        {
            printRejection();
        }
    }

    /**
     * Method for performing a deposit.
     * Lets user input amount of cash they want to deposit. Checks for bank verification.
     * If transaction is rejected, printRejection is run.
     * If transaction is accepted, a Deposit object is created.
     * If deposit object verifies that check has been inserted, the printReceipt method is called.
     * If the deposit object determines a check has not been inserted, the printRejection method is called.
     */
    public void deposit()
    {
        System.out.println("How much Cash would you like to deposit?");
        int amountCash = scanner.nextInt();
        approved = approvalMessage.getApproval();
        if (approved)
        {
            Deposit deposit = new Deposit(amountCash);
            if (deposit.checkSlip())
            {
                deposit.depositMessage();
                printReceipt(menuChoice);
                amountDeposited = amountCash;
            }
             else
            {
                printRejection();
            }
        }
        else
        {
            printRejection();
        }
    }

    /**
     * Allows a user to transfer money from one account to another.
     * Prompts user for account to take money from, account to send money to, and amount of money to send.
     * Verifies transaction with the bank.
     * If verified, a printReceipt is run.
     * If not verified, a printRejection is run.
     */
    public void transfer()
    {
        System.out.println("Which account would you like to transfer from?");
        String accountFrom = scanner.next();
        System.out.println("Which account would you like to transfer to?");
        String accountTo = scanner.next();
        System.out.println("How much money would you like to transfer?");
        int amountCash = scanner.nextInt();
        approved = approvalMessage.getApproval();
        if (approved)
        {
            printReceipt(menuChoice);
        }
        else
        {
            printRejection();
        }
    }

    /**
     * Allows user to check the balance of an account.
     * Checks for bank verification. If verified, the printReceipt method is called.
     */
    public void checkBalance()
    {
        approved = approvalMessage.getApproval();
        if (approved)
        {
            printReceipt(menuChoice);
        }
        else
        {
           printRejection();
        }
    }

    /**
     * Creates a printer object and instructs the printer object to print an appropriate receipt.
     * @param menuChoice So the printer knows what type of receipt to print
     */
    public void printReceipt(int menuChoice)
    {
        Printer printer = new Printer(menuChoice);
        printer.printReceipt();
    }

    /**
     * Prints rejection notice
     */
    public void printRejection()
    {
        System.out.println("I'm sorry, your transaction has been rejected for *reason*");
    }

    /**
     * Prompts user to input whether they would like to make another transaction or not.
     * If user selects no, the removeCard method of the reader is called.
     * @return userContinue
     * @todo Maybe input sanitization. Or leave it, whatever it still works the way it is.
     */
    public boolean promptContinue(boolean sameUser)
    {
        if (reader.getCardIn() || sameUser)
        {
            System.out.println("Would you like to make another transaction? If yes, type *Yes* If no, please type *No*");
            String continueOption = scanner.next();
            if (continueOption.equals("*Yes*"))
            {
                userContinue = true;
            }
            else
            {
                reader.removeCard();
                userContinue = false;
            }
        }
        return userContinue;
    }

    /**
     * @return cardNum
     */
    public long getCardNum()
    {
        return cardNum;
    }

    /**
     * @return cardApproved
     */
    public boolean getCardApproved()
    {
        return cardApproved;
    }

    /**
     * Prints a message when card is rejected.
     */
    public void refuseCard()
    {
        System.out.println("Your card is invalid. Please try again with a different card.");
    }
}