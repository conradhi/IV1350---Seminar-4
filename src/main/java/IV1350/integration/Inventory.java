package IV1350.integration;

import IV1350.model.Sale;
import IV1350.model.Quantity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Keeps log of all the available {@Item} in the store's inventory.
 */
public class Inventory {
    private HashMap<String, Item> inventory = new HashMap();

    /**
     * Adds items to the inventory because there is no real database.
     * Just hard coded {@Item}
     */
    public Inventory(){
        addItems();
    }

    /**
     * Decrease the amount of items in the inventory after a {@Sale}
     * 
     * @param sale The finished sale. 
     */
    public void updateInventory(Sale sale){
        HashMap<String, Quantity> items = sale.getItems();
        Set entries = items.entrySet();

        for (HashMap.Entry<String, Quantity> set : items.entrySet()) {
            Item item = getItem(set.getKey());
            int quantity = sale.getItemsQuantity(item);


            if (itemExists(set.getKey())) {
                decreaseQuantity(item, quantity);
            }
        }
    }

    /**
     * Checks if an item exists in the inventory
     * 
     * @param itemIdentifier Identifier of the {@Item} that we are looking for
     * @return True or false depending on if item was found or not
     */
    public boolean itemExists(String itemIdentifier){
        if(inventory.containsKey(itemIdentifier)){
            return true;
        }
        else{
            return false;
        }
    }

    private void decreaseQuantity(Item item, int quantity){
        
        Item oldItem = inventory.get(item.getItemIdentifier());
        oldItem.decreaseQuantity(quantity);
        inventory.put(oldItem.getItemIdentifier(), oldItem);
    }

    /**
     * Returns the specified {@Item}
     * 
     * @param itemIdentifier The string that identifies the {@Item}
     * @return The specified {@Item}
     */
    public Item getItem(String itemIdentifier){
        return inventory.get(itemIdentifier);
    }

    private void addItems(){
        inventory.put("1001", new Item("1001", "Apple", 5, 100, 1.3));
        inventory.put("1002", new Item("1002", "Banan", 7, 100, 1.3));
        inventory.put("1003", new Item("1003", "Mango", 15, 100, 1.3));
        inventory.put("1004", new Item("1004", "Kiwi", 10, 100, 1.3));
        
    }
}