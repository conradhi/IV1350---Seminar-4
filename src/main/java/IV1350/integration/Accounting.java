package IV1350.integration;

import IV1350.model.Sale;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Keeps track of all the {@link Sale} made and stores it in a map with the
 * <code>LocalDateTime</code> as a key. 
 */
public class Accounting {
    private HashMap<LocalDateTime, Sale> accounting = new HashMap();

    /**
     * Constructs the class
     */
    public Accounting(){ }

    /**
     * Stores the {@link Sale} in a <code>HashMap</code>
     * 
     * @param sale The finished sale. 
     */
    public void bookKeep(Sale sale){
        LocalDateTime saleTime = LocalDateTime.now();
        accounting.put(saleTime, sale);
    }
}