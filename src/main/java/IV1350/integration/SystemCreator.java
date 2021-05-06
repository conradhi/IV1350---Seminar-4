package IV1350.integration;

/**
 * Initializes the {@Accounting} and the {@Inventory}
 */
public class SystemCreator {
    private Accounting accounting = new Accounting();
    private Inventory inventory = new Inventory();

    /**
     * Constructor
     */
    public SystemCreator(){
    }

    /**
     * Initializes {@Accounting}.
     * 
     * @return The {@Accounting} system
     */
    public Accounting getAccounting() {
        return accounting;
    }

    /**
     * Initializes {@Inventory}.
     * 
     * @return  The {@Inventory} system.
     */
    public Inventory getInventory() {
        return this.inventory;

    }
}