package IV1350.controller;

import IV1350.integration.*;
import IV1350.model.Receipt;
import IV1350.model.Sale;
import IV1350.model.SaleObserver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * All calls to the program are passed through the controller. 
 * startNewSale() needs to be executed first for the program to work. 
 * pay() is the last method to be called as it will reset the sale class. 
 */
public class Controller implements SaleObserver{
    private Accounting accounting;
    private Inventory inventory;
    private Printer printer;
    private Register register;
    private Sale sale;
    private List<SaleObserver> saleObservers = new ArrayList<>();

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
     * @throws IV1350.integration.ItemNotFoundException
     *         when an Item with ItemIdentifier can not be found in the inventory
     * @throws IV1350.integration.ConnectionFailException
     *         when connection to the inventory database fails
     */
    public String registerItem(String itemIdentifier) throws ItemNotFoundException, ConnectionFailException {
            Item item = inventory.getItem(itemIdentifier);
            sale.updateSale(item);
            return "Namn: " + item.getName() + "     Pris: " + item.getPrice() + "kr    Antal: " + sale.getSaleQuantity(itemIdentifier) + "     Totalt: " 
            + sale.getTotal().getTotal() + "kr     Med moms: " + getTotalWithTax() + "kr";
    }

    /**
     * This method is called when there are no more items to scan.
     * 
     * @return A string stating the total to pay and the amount of tax. 
     */
    public String endSale(){
        String moms = calculateMoms();
        return "\n" + "Att betala:" + getTotalWithTax() + "kr      Varav moms: " + moms + "kr";
    }

    /**
     * The class that finishes the sale and needs to be called last. Updates accounting with
     * the sale information. Updates the quantity of the inventory for scanned items. 
     * Prints a receipt. Adds cash to the register with the given {@link amount}.
     * 
     * @param payment The amount of money that the customer pays.
     */
    public void pay(double payment){
        sale.addObserver(saleObservers);
        sale.pay(payment);
        accounting.bookKeep(sale);
        inventory.updateInventory(sale);
        Receipt receipt = new Receipt(sale);
        printer.printReceipt(receipt);
        register.addPayment(payment);
        sale = null;
    }

    private String calculateMoms(){
        double moms = getTotalWithTax() - sale.getTotal().getTotal();
        return decimalFormat.format(moms);
    }

    private double getTotalWithTax(){
        return sale.getTotal().getTotalWithTax();
    }
    
     /**
     * The specified observer will be notified of the total when a payment have been made.
     * There will only be notifications after this method is called
     *
     * @param saleObserver The observer to notify.
     */
    public void addSaleObserver(SaleObserver saleObserver){
        saleObservers.add(saleObserver);
    }

    @Override
    public void completedPayment(double amount) {
        
    }
    
}