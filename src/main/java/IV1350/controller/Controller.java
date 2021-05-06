package IV1350.controller;

import IV1350.integration.*;
import IV1350.model.Receipt;
import IV1350.model.Sale;

import java.text.DecimalFormat;

/**
 * All calls to the program are passed through the controller. 
 * startNewSale() needs to be executed first for the program to work. 
 * pay() is the last method to be called as it will reset the sale class. 
 */
public class Controller {
    private Accounting accounting;
    private Inventory inventory;
    private Printer printer;
    private Register register;
    private Sale sale;

    private String pattern = "###,###.###";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);

    /**
     * Creates the controller and starts the necessary systems.
     * 
     * @param printer           Will print the receipt after a sale is done.
     * @param register          Holds the current cash amount in the register.
     * @param systemCreator     Sets up inventory and accounting systems. 
     */
    public Controller(Printer printer, Register register, SystemCreator systemCreator){
        this.accounting = systemCreator.getAccounting();
        this.inventory = systemCreator.getInventory();
        this.printer = printer;
        this.register = register;
    }

    /**
     * Starts a new sale.
     */
    public void startNewSale(){
        this.sale = new Sale();
    }

    /**
     * If the item exists in the inventory it will be added to the sale class and a string containing 
     * item and sale information will be printed.
     * 
     * @param itemIdentifier The string that identifies the item.
     * @return If the item exists a string containing item name, price, quantity, running total and 
     *         running total with tax will be printed. If the item does not exist, an error message is printed. 
     */
    public String registerItem(String itemIdentifier){
        if (itemExists(itemIdentifier)){
            Item item = inventory.getItem(itemIdentifier);
            sale.updateSale(item);
            return "Namn: " + item.getName() + "     Pris: " + item.getPrice() + "kr    Antal: " + sale.getSaleQuantity(itemIdentifier) + "     Totalt: " 
            + sale.getTotal().getTotal() + "kr     Med moms: " + getTotalWithTax() + "kr";
        }
        else{
            return "Item not found";
        }
    }

    /**
     * This method is called when there are no more items to scan.
     * 
     * @return A string stating the total to pay and the amount of tax. 
     */
    public String endSale(){
        String moms = calculateMoms();
        return "\n" + "Att betala: " + getTotalWithTax() + "kr      Varav moms: " + moms + "kr" ;
    }

    /**
     * The class that finishes the sale and needs to be called last. Updates accounting with
     * the sale information. Updates the quantity of the inventory for scanned items. 
     * Prints a receipt. Adds cash to the register with the given {@link amount}.
     * 
     * @param payment The amount of money that the customer pays.
     */
    public void pay(double payment){
        sale.pay(payment);
        double change = sale.getPayment() - getTotalWithTax();
        accounting.bookKeep(sale);
        inventory.updateInventory(sale);
        Receipt receipt = new Receipt(sale);
        printer.printReceipt(receipt);
        register.addPayment(payment);
        sale = null;
    }

    private boolean itemExists(String itemIdentifier){
        return inventory.itemExists(itemIdentifier);
    }

    private String calculateMoms(){
        double moms = getTotalWithTax() - sale.getTotal().getTotal();
        return decimalFormat.format(moms);
    }

    private double getTotalWithTax(){
        return sale.getTotal().getTotalWithTax();
    }

}