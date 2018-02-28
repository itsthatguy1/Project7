/**
 * @author Sean Stock
 * @version 2.27.18
 * @todo Make it so that only multiples of 20 can be dispensed.
 */

public class Dispenser
{
    private int amountCash;

    /**
     *
     */
    public Dispenser(int amountCash)
    {
        this.amountCash = amountCash;
    }

    /**
     *
     */
    public void dispenseCash()
    {
        System.out.println("*Dispensed $" + amountCash + "*");
        System.out.println("Thank you.");
    }
}
