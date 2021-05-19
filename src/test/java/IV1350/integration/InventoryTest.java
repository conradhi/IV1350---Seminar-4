/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IV1350.integration;

import IV1350.integration.Inventory;
import IV1350.integration.Item;
import IV1350.controller.Controller;
import IV1350.integration.Printer;
import IV1350.integration.Register;
import IV1350.integration.SystemCreator;
import IV1350.model.Sale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Conrad
 */
public class InventoryTest {
    private Inventory inventory;
   // private Controller controller;
            
    @Before
    public void setUpClass() {
        inventory = new Inventory();
    }

    @After
    public void tearDownClass(){
       inventory = null;
    }
    /**
     * Test of updateInventory method, of class Inventory.
     */
    @Test
    public void testUpdateInventory() {
        Sale sale = new Sale();
        Item item = new Item("1001", "Apple", 5, 100, 1.3);
        sale.updateSale(item);
        inventory.updateInventory(sale);
        Item itemExpected = new Item("1001", "Apple", 5, 99, 1.3);
        int expectedResult = itemExpected.getQuantityInStock();
        try{
            int actualResult = inventory.getItem("1001").getQuantityInStock();
            assertEquals("Inventory quantity has not been changed properly", expectedResult, actualResult);
        }catch (ItemNotFoundException exc){
            fail("Item was not found in inventory");
        }
        catch (ConnectionFailException exc){
            fail("No connection to inventory system");
        }
    }

}
