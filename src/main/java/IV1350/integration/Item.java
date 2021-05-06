package IV1350.integration;

public class Item {
    private String itemIdentifier;
    private String name;
    private int price;
    private int quantityInStock;
    private double vat;

    /**
     * Stores all the information about available {@Item} that are available to 
     * buy in the store. 
     * 
     * @param itemIdentifier 
     * @param name
     * @param price Without tax
     * @param quantity Available amount in the inventory
     * @param vat The amount of tax that will be multiplied with the price.
     */
    public Item(String itemIdentifier, String name, int price, int quantity, double vat) {
        this.itemIdentifier = itemIdentifier;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantity;
        this.vat = vat;
    }

    /**
     * Decreases the amount by the specified value.
     * 
     * @param otherQuantity The quantity to be removed.
     */
    public void decreaseQuantity(int otherQuantity){
        this.quantityInStock -= otherQuantity;
    }

    /**
     * Returns the string that identifies the {@Item}
     * 
     * @return Item identifier
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Returns the name of the {@Item}
     * 
     * @return Name of {@Item}
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the {@Item}
     * 
     * @return Price of {@Item}
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the available amount of {@Item} in the stock.
     * 
     * @return Number of {@Item} available in stock.
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * Returns the number that the {@Item} will be multiplied by to 
     * calculate the price to pay. 
     * 
     * @return The tax amount to be multiplied.
     */
    public double getVat() {
        return vat;
    }

}