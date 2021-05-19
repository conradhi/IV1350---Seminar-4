/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IV1350.integration;

/**
 *
 * @author Conrad
 */
public class ItemNotFoundException extends Exception {
    private String itemIdentifier;
    private String message;
    
    /**
     * Creates a new instance of ItemNotFoundException
     * 
     * @param itemIdentifier the ItemIdentifier that was not found in the inventory
     * @param message Message to be displayed when exception is thrown
     */
    public ItemNotFoundException(String itemIdentifier, String message) {
        super(message);
        this.message = message;
        this.itemIdentifier = itemIdentifier;
        
    }
    
    /**
     * Returns the ItemIdentifier that was not found in the inventory
     * 
     * @return the ItemIdentifier that was not found in the inventory
     */
    public String getItemIdentifier() {
        return this.itemIdentifier;
    }
    
    public String getMessage() {
        return this.message;
    }
}
