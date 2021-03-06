package IV1350.view;

import IV1350.model.Logger;
import IV1350.model.SaleObserver;

/**
 *
 * @author Cobrad
 * 
 * Displays total revenue earned since start of the program
 */
public class TotalRevenueView implements SaleObserver {
    private double revenue;
    /**
     * Creates a new instance of TotalRevenueView.
     */
    public TotalRevenueView() {
       revenue = 0.0;
    }
    
    /**
     * Notifies observers of a completed payment with the amount.
     * 
     * @param amount The amount of the payment.
     */
    @Override
    public void completedPayment(double amount) {
        revenue += amount;
        printTotalRevenue();
    }
    
    private void printTotalRevenue() {
        System.out.println("\n" + "****** DISPLAY ******");
        System.out.println("\n" + "*** TOTAL REVENUE ***");
        System.out.println("   Amount: " + revenue);
        System.out.println("*********************\n");
    }

}