package IV1350.model;

import IV1350.integration.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

/**
 * Keeps track of all the information in an ongoing {@Sale}.
 */
public class Sale {
    private Total total;
    private HashMap<String, Quantity> quantityMap = new HashMap<>();
    private double payment = 0;
    private List<SaleObserver> saleObservers = new ArrayList();
    
    private String pattern = "###,###.###";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);

    /**
     * Creates a new {@Total} for the current {@Sale}.
     */
    public Sale(){
        this.total = new Total();
    }

    /**
     * returns the {@Total} of the current {@Sale}
     * 
     * @return {@Total} of the current {@Sale}
     */
    public Total getTotal() {
        return total;
    }

    /**
     * Finds the amount of the scanned {@Item} in the current {@Sale}.
     * 
     * @param item That is scanned 
     * @return 
     */
    public int getItemsQuantity(Item item){
        return  quantityMap.get(item.getItemIdentifier()).getQuantity();
    }

    /**
     * If an {@item} is not in the {@Sale} it returns a String of 0.
     * Otherwise it returns a String representation of the quantity of an {@Item}.
     * 
     * @param itemIdentifier of scanned {@Item}
     * @return String representation of the amount in the current {@Sale}.
     */
    public String getSaleQuantity(String itemIdentifier){
        if(quantityMap.get(itemIdentifier) == null){
            return "0";
        }
        else{
            return Integer.toString(quantityMap.get(itemIdentifier).getQuantity());
        }
    }

    /**
     * Stores the amount that the customer uses to pay for the {@Sale}
     * 
     * @param payment The amount of cash given by the customer.
     */
    public void pay(double payment){
        this.payment = payment;
        notifyObservers();
    }

    /**
     * returns the paid amount. 
     * 
     * @return the paid amount.
     */
    public double getPayment(){
        return payment;
    }

    /**
     * Returns a <code>HashMap</code> of all the {@Item} and their {@Quantity}
     * in the current {@Sale}
     *
     * @return <code>HashMap</code> of all the {@Item} and their {@Quantity}
     *         in the current {@Sale}
     */
    public HashMap<String, Quantity> getItems(){
        return quantityMap;
    }

    /**
     * Adds a scanned {@Item} to the {@Sale}.
     * 
     * @param item that is scanned.
     */
    public void updateSale(Item item){
        if (itemListContains(item)) {
            updateItemQuantityAndTotal(item);
        } else {
            addItemAndUpdateTotal(item);
        }
    }

    private boolean itemListContains(Item item){
        return quantityMap.containsKey(item.getItemIdentifier());
    }

    private void addItemAndUpdateTotal(Item item){
        Quantity quantity = new Quantity(item);
        quantityMap.put(item.getItemIdentifier(), quantity);
        total.updateTotal(item);
    }

    private void updateItemQuantityAndTotal(Item item){
        Quantity quantity = quantityMap.get(item.getItemIdentifier());
        quantity.updateQuantity();
        total.updateTotal(item);
    }

    /**
     * A string builder of the current {@Sale} that will be put on the {@Receipt}.
     * 
     * @return a string representation of the current {@Sale}.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Iterator entriesIterator = getEntries();

        while(entriesIterator.hasNext()) {
            Quantity quantity = getCurrentItem(entriesIterator);
            builder.append(quantity.getItem().getName());
            addNewLine(builder, " Antal: " + quantity.getQuantity());
        }

        addNewLine(builder, "\n" + "Att betala: " + total.getTotalWithTax());
        addNewLine(builder, "Moms: " + decimalFormat.format(total.getTotalWithTax() - total.getTotal()));

        addNewLine(builder, "\n" + "Betalt kontant: " + payment);
        addNewLine(builder, "Retur: " + decimalFormat.format(payment - total.getTotalWithTax()));
        return builder.toString();
    }

    private Iterator getEntries(){
        Set entries = quantityMap.entrySet();
        return entries.iterator();
    }

    private Quantity getCurrentItem(Iterator entriesIterator){
        Map.Entry mapping = (Map.Entry) entriesIterator.next();
        return (Quantity) mapping.getValue();
    }

    private void addNewLine(StringBuilder builder, String line){
        builder.append(line);
        builder.append("\n");
    }
    
     /**
     * Adds a new observer to the current sale which will be notified when a payment is made
     * 
     * @param observer The observer that will be notified of a payment.
     */
     /**
     * Registers observers. Any <code>Observer</code> that is passed to this method will be notified
     * when this object changes state.
     *
     * @param observers The observer that shall be registered.
     */
    public void addObserver(List<SaleObserver> observers) {
        saleObservers.addAll(observers);
    }
    
    private void notifyObservers() {
        for (SaleObserver observer : saleObservers) {
            observer.completedPayment(payment);
        }
    }
}