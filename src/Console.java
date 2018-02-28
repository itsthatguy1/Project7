/**
 * @author Sean Stock
 * @version 2.27.18
 */

import java.util.Scanner;

public class Console
{
    private Scanner scanner;
    private Reader reader;
    private Deposit deposit;
    private Printer printer;
    private Dispenser dispenser;
    private Bank bank;
    private int menuChoice;

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Console console = new Console();
        console.menu();
        console.withdrawal();
    }

    /**
     *
     */
    public Console()
    {
        scanner = new Scanner(System.in);
        reader = new Reader();
        printer = new Printer();
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
    }

    /**
     *
     */
    public void withdrawal()
    {
        System.out.println("How much Cash would you like to withdraw?");
        int amountCash = scanner.nextInt();
        scanner.close();
        Dispenser dispenser = new Dispenser(amountCash);
        dispenser.dispenseCash();
    }

    /**
     *
     */
    public void deposit()
    {
        Deposit deposit = new Deposit();

    }

    /**
     *
     */
    public void transfer()
    {
    }

    /**
     *
     */
    public void checkBalance()
    {
    }
}
