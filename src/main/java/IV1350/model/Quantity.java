package IV1350.model;

import IV1350.integration.Item;

/**
 * If a {@Sale} has multiples of the same {@Item}, this class 
 * will keep track of that. 
 */
public class Quantity {
    private Item item;
    private int quantity = 1;

    /**
     * Stores the {@Item} when it is first scanned. 
     * 
     * @param item that is scanned in the {@Sale}
     */
    public Quantity(Item item){
        this.item = item;
    }

    /**
     * Returns the {@Item} that is tracked. 
     * 
     * @return {@Item} that was requested. 
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the quantity of this {@Item} in the current {@Sale}.
     * 
     * @return quantity of this {@Item} in the current {@Sale}.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Every {@Item} is scanned one by one so this class increments the
     * quantity by 1.
     */
    public void updateQuantity(){
        quantity++;
    }
}