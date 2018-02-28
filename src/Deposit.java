/**
 * @author Sean Stock
 * @version 2.27.18
 */

public class Deposit
{
    private int amountCash;

    /**
     *
     */
    public Deposit(int amountCash)
    {
        this.amountCash = amountCash;
    }

    /**
     *
     */
    public void depositSlip()
    {
        System.out.println("Successfully deposited $" + amountCash + " to some account");
        System.out.println("Thank you.");
    }
}
