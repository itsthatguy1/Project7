/**
 * Represents the console of the ATM which controls each of the separate ATM parts to complete a transaction.
 * Each console object represents a transaction.
 * @author Sean Stock
 * @version 2.27.18
 * @todo Make an option to cancel during any point
 * @todo add gitignore
 * @todo INPUT SANITIZATION
 * @todo make it so that user can continue transactions until they wish to stop, at which point card will be returned.
 * @todo investigate making some things static and handling stuff in main method instead of constructor.
 */

import java.util.Scanner;

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
    private static final int MAX_TRIES = 3; //Check to see if this can be local instead of a field

    /**
     * Main method in class Console
     * @param args
     */
    public static void main(String[] args)
    {
        Console console = new Console();
    }

    /**
     * Constructor of class Console.
     */
    public Console()
    {
        amountDeposited = 0;
        amountWithdrawn = 0;
        userContinue = false;
        scanner = new Scanner(System.in);
        reader = new Reader();
        approvalMessage = new Bank();
        insertCard();
    }

    /**
     * Lets the card reader accept a card. If a card is inserted, the checkPin method is run.
     */
    public void insertCard()
    {
        boolean hasInserted = reader.insertCard();
        if (hasInserted)
        {
            checkPIN();
        }
    }

    /**
     * Lets the reader accept a PIN input from the user.
     * If the reader determines that the PIN is incorrect, the user is prompted for their PIN. If a user enters an incorrect PIN three times, the transaction is cancelled.
     * If the user inputs a correct PIN, the menu method is run.
     */
    public void checkPIN()
    {
        int tries = 1;
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
     * Lets user input amount of cash they want. Checks for bank verification. If transaction is rejected, printRejection method is called.
     * If transaction is accepted, a Dispenser object is created and instructed to dispense the appropriate amount of cash. Afterward, the printReceipt method is called.
     */
    public void withdrawal()
    {
        System.out.println("How much Cash would you like to withdraw?");
        int amountCash = scanner.nextInt();
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
     * Lets user input amount of cash they want to deposit. Checks for bank verification. If transaction is rejected, printRejection is run.
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
                deposit.depositSlip();
                printReceipt(menuChoice);
                amountDeposited = amountCash;
            }
             if (!deposit.checkSlip())
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
     *@todo create a better way to store money in & out of accounts in log
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
     * @param menuChoice
     */
    public void printReceipt(int menuChoice)
    {
        Printer printer = new Printer(menuChoice);
        printer.printReceipt();
    }

    /**
     * Creates a printer object and instructs the printer object to print a rejection.
     */
    public void printRejection()
    {
        Printer printer = new Printer(menuChoice);
        printer.printRejection();
    }
}
