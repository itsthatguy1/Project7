/**
 * @author Sean Stock
 * @version 2.27.18
 * @todo clear screen after each transaction is done
 */

import java.util.ArrayList;

public class ATM
{
    private ArrayList<Console> transactions;
    private Console transaction;
    private boolean machineOn;

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ATM atm = new ATM();
    }

    /**
     *
     */
    public ATM()
    {
        transactions = new ArrayList<>();
        machineOn = true;
        addTransaction();
    }

    /**
     *
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
