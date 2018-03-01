/**
 * Creates Console objects and stores them in an array.
 * @author Sean Stock
 * @version 2.27.18
 */

import java.util.ArrayList;

public class ATM
{
    private ArrayList<Console> transactions;
    private Console transaction;
    private boolean machineOn;

    /**
     * Main method of class ATM.
     * @param args
     */
    public static void main(String[] args)
    {
        ATM atm = new ATM();
    }

    /**
     * Constructor of class ATM
     */
    public ATM()
    {
        transactions = new ArrayList<>();
        machineOn = true;
        addTransaction();
    }

    /**
     * Creates new Console objects and stores them in an array. Will continue to loop while the machine is on.
     */
    public void addTransaction()
    {
        while (machineOn)
        {
            transaction = new Console();
            transactions.add(transaction);
        }
    }
}
