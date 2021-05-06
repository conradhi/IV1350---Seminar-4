package IV1350.integration;

import IV1350.model.Receipt;

/**
 * Prints the receipt of the finished {@Sale}
 */
public class Printer {

    /**
     * Creates the printer
     */
    public Printer(){ }

    /**
     * Prints the given {@Receipt} to System.out
     * 
     * @param receipt Of the completed {@Sale} 
     */
    public void printReceipt(Receipt receipt){
        System.out.println(receipt.toString());
    }
}