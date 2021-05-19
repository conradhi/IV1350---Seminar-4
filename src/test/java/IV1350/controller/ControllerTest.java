/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IV1350.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import IV1350.integration.Printer;
import IV1350.integration.SystemCreator;
import IV1350.integration.Register;
import IV1350.integration.ItemNotFoundException;
import IV1350.integration.ConnectionFailException;


import static org.junit.Assert.*;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUpClass() {
        controller = new Controller(new Printer(), new Register(), new SystemCreator());
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void registerItem() {
        controller.startNewSale();
        String itemIdentifier = "1001";
        String itemName = "Apple";
        int itemPrice = 5;
        int quantity = 1;
        int saleTotal = 5;
        double saleTotalWithTax = 6.5;
       try{
        String actualResult = controller.registerItem(itemIdentifier);
        String expectedResult = "Namn: " + itemName + "     Pris: " + itemPrice + "kr    Antal: " 
                + quantity + "     Totalt: " + saleTotal + "kr     Med moms: " + saleTotalWithTax + "kr";
        assertEquals("String from registerItem is not the same as String with the same state.", expectedResult, actualResult);
        }catch (ItemNotFoundException exc){
            fail("Item was not found in inventory");
        }
        catch (ConnectionFailException exc){
            fail("No connection to inventory system");
        }
    }
    
    @Test
    public void registerInvalidItem() {
        controller.startNewSale();
        String itemIdentifier = "1111";
        try{
            String actualResult = controller.registerItem(itemIdentifier);
            fail("could register invalid item");
        }catch (ItemNotFoundException exc){
            assertTrue(exc.getMessage().contains("Could not find item with the identifier " + itemIdentifier + " in the inventory system")); 
        }
        catch (ConnectionFailException exc){
            fail("No connection to inventory system");
        }
    }
    
    @Test
    public void connectionFail() {
        controller.startNewSale();
        String itemIdentifier = "111";
        try{
            String actualResult = controller.registerItem(itemIdentifier);
            String expectedResult = "Item not found";
            assertEquals("String from registerItem is not the same as String with the same state.", expectedResult, actualResult);
        }catch (ItemNotFoundException exc){
            fail("Connection could be established even though inventory is offline");
        }
        catch (ConnectionFailException exc){
            assertTrue(exc.getMessage().contains("Could not connect to database"));
        }
    }
        
    
    @Test
    public void registerSameItem() {
        controller.startNewSale();
        try{
            controller.registerItem("1001");
            controller.registerItem("1001");
            controller.registerItem("1001");
            String itemIdentifier = "1001";
            String itemName = "Apple";
            int itemPrice = 5;
            int quantity = 4;
            int saleTotal = 20;
            double saleTotalWithTax = 26.0;

            String actualResult = controller.registerItem(itemIdentifier);
            String expectedResult = "Namn: " + itemName + "     Pris: " + itemPrice + "kr    Antal: " 
                    + quantity + "     Totalt: " + saleTotal + "kr     Med moms: " + saleTotalWithTax + "kr";
            assertEquals("String from registerItem is not the same as String with the same state.", expectedResult, actualResult);
        }catch (ItemNotFoundException exc){
            fail("Item was not found in inventory");
        }
        catch (ConnectionFailException exc){
            fail("No connection to inventory system");
        }
    }
    
    @Test
    public void registerDifferentItems() {
        controller.startNewSale();
        try{
            controller.registerItem("1001");
            controller.registerItem("1002");
            controller.registerItem("1003");
            String itemIdentifier = "1001";
            String itemName = "Apple";
            int itemPrice = 5;
            int quantity = 2;
            int saleTotal = 32;
            double saleTotalWithTax = 41.6;

            String actualResult = controller.registerItem(itemIdentifier);
            String expectedResult = "Namn: " + itemName + "     Pris: " + itemPrice + "kr    Antal: " 
                    + quantity + "     Totalt: " + saleTotal + "kr     Med moms: " + saleTotalWithTax + "kr";
            assertEquals("String from registerItem is not the same as String with the same state.", expectedResult, actualResult);
        }catch (ItemNotFoundException exc){
            fail("Item was not found in inventory");
        }
        catch (ConnectionFailException exc){
            fail("No connection to inventory system");
        }
    }
    
    @Test
    public void endSale() {
        controller.startNewSale();
        try{
            controller.registerItem("1001");
            controller.registerItem("1002");
            controller.registerItem("1003");
            double moms = 8.1;
            double saleTotalWithTax = 35.1;

            String actualResult = controller.endSale();
            String expectedResult = "\n" + "Att betala:" + saleTotalWithTax + "kr      Varav moms: " + moms + "kr";
            assertEquals("String from registerItem is not the same as String with the same state.", expectedResult, actualResult);
        }catch (ItemNotFoundException exc){
            fail("Item was not found in inventory");
        }
        catch (ConnectionFailException exc){
            fail("No connection to inventory system");
        }
    }
    
}