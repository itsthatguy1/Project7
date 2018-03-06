/**
 * Dispenses cash to user.
 * @author Sean Stock
 * @version 2.27.18
 */


public class Dispenser
{
    private int amountCash;

    /**
     * Constructor method of class Dispenser.
     * @param amountCash
     */
    public Dispenser(int amountCash)
    {
        this.amountCash = amountCash;
    }

    /**
     * Dispenses Cash to user.
     */
    public void dispenseCash()
    {
        System.out.println("*Dispensed $" + amountCash + "*");
        System.out.println("Thank you.");
    }
}