/**
 * Prints a receipt or rejection notice to user.
 * @author Sean Stock
 * @version 2.27.
 * @todo Put some actual variables in the receipt.
 */

public class Printer
{
    private int menuChoice;

    /**
     * Constructor of class Printer.
     * @param menuChoice
     */
    public Printer(int menuChoice)
    {
        this.menuChoice = menuChoice;
    }

    /**
     * Prints one of four receipts based on the type of transaction that took place.
     */
    public void printReceipt()
    {
        switch (menuChoice)
        {
            case 1: System.out.println("Receipt:");
                    System.out.println("Some Date");
                    System.out.println("Some Time");
                    System.out.println("Some Location");
                    System.out.println("Withdrawal from some account");
                    System.out.println("Amount: Some amount");
                    System.out.println("Old balance: something");
                    System.out.println("New balance: something");
                    break;
            case 2: System.out.println("Receipt:");
                    System.out.println("Some Date");
                    System.out.println("Some Time");
                    System.out.println("Some Location");
                    System.out.println("Deposit to some account");
                    System.out.println("Amount: Some amount");
                    System.out.println("Old balance: something");
                    System.out.println("New balance: something");
                    break;
            case 3: System.out.println("Receipt:");
                    System.out.println("Some Date");
                    System.out.println("Some Time");
                    System.out.println("Some Location");
                    System.out.println("Transfer from account a to account b");
                    System.out.println("Amount: Some amount");
                    System.out.println("Old balance: something");
                    System.out.println("New balance: something");
                    break;
            case 4: System.out.println("Receipt:");
                    System.out.println("Some Date");
                    System.out.println("Some Time");
                    System.out.println("Some Location");
                    System.out.println("Amount: Some amount");
                    break;
        }

    }
}