package IV1350.integration;

/**
 * Keeps track of the amount of cash available in the register
 */
public class Register {
    private double balance;

    /**
     * Sets starting balance to 0.
     */
    public Register(){
        this.balance = 0;
    }

    /**
     * Returns current cash available in the register.
     * 
     * @return Current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Adds cash to the register after a completed {@Sale}.
     * 
     * @param payment That will be added to the register balance. 
     */
    public void addPayment(double payment){
        balance += payment;
    }
}