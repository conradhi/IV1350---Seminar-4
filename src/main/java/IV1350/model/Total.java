package IV1350.model;

import IV1350.integration.Item;

/**
 * keeps track of the total with and without tax of every ongoing {@Sale}.
 */
public class Total {
    private int total = 0;
    private double totalWithTax = 0;

    /**
     * Constructor
     */
    public Total(){

    }

    /**
     * @return total without tax
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return total with tax
     */
    public double getTotalWithTax() {
        return totalWithTax;
    }

    /**
     * When an {@Item} is scanned for the ongoing {@Sale}
     * The total with and without tax is calculated here. 
     * 
     * @param item to be added to total with and without tax. 
     */
    public void updateTotal(Item item){
        total += item.getPrice();
        totalWithTax += item.getVat() * item.getPrice();
    }
}