package IV1350.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints log messages to a file. The log file will be in the current directory and will be called
 * log.txt.
 */
public class TotalRevenueFileOutput implements Logger, SaleObserver {
    private PrintWriter logStream;
    private double revenue;

    /**
     * Creates a new instance and also creates a new log file. An existing log file will be deleted.
     */
    public TotalRevenueFileOutput() {
        revenue = 0.0;
        try {
            logStream = new PrintWriter(new FileWriter("log.txt"), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file.
     *
     * @param revenue The amount of revenue that will be printed to the log file.
     */
    @Override
    public void log(double revenue) {
        logStream.println("Current revenue in register: " + revenue + "SEK");
    }

    /**
     * Notifies observers of a completed payment with the amount.
     * 
     * @param amount The amount of the payment.
     */
    @Override
    public void completedPayment(double amount) {
        revenue += amount;
        log(revenue);
    }

}