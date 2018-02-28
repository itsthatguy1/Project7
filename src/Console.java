/**
 * @author Sean Stock
 * @version 2.27.18
 * @todo Make an option to cancel during any point
 * @todo add gitignore
 * @todo add functionality for bank rejection
 */

import java.util.Scanner;

public class Console
{
    private Scanner scanner;
    private Reader reader;
    private Bank bank;
    private int menuChoice;
    private boolean approved;

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Console console = new Console();
    }

    /**
     *
     */
    public Console()
    {
        scanner = new Scanner(System.in);
        reader = new Reader();
        bank = new Bank();
        menu();
    }

    /**
     *
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
                    printReceipt(menuChoice);
                    break;
            case 2: deposit();
                    printReceipt(menuChoice);
                    break;
            case 3: transfer();
                    printReceipt(menuChoice);
                    break;
            case 4: checkBalance();
                    printReceipt(menuChoice);
                    break;
        }

    }

    /**
     *
     */
    public void withdrawal()
    {
        System.out.println("How much Cash would you like to withdraw?");
        int amountCash = scanner.nextInt();
        //scanner.close();
        approved = bank.getApproval();
        if (approved)
        {
            Dispenser dispenser = new Dispenser(amountCash);
            dispenser.dispenseCash();
        }
    }

    /**
     *
     */
    public void deposit()
    {
        System.out.println("How much Cash would you like to deposit?");
        int amountCash = scanner.nextInt();
        //2scanner.close();
        approved = bank.getApproval();
        if (approved)
        {
            Deposit deposit = new Deposit(amountCash);
            deposit.depositSlip();
        }
    }

    /**
     *
     */
    public void transfer()
    {
        approved = bank.getApproval();
        if (approved)
        {
        }
    }

    /**
     *
     */
    public void checkBalance()
    {
        approved = bank.getApproval();
        if (approved)
        {
        }
    }

    /**
     *
     */
    public void printReceipt(int menuChoice)
    {
        Printer printer = new Printer(menuChoice);
        printer.printReceipt();
    }
}
