package IV1350.model;


/**
 *
 * @author Conrad
 * 
 * This is an interface that observes sales.
 */
public interface SaleObserver {
    
    /**
     * Notifies observers of a completed payment with the amount.
     * 
     * @param amount The amount of the payment.
     */
    void completedPayment(double amount);
}