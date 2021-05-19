package IV1350.view;

import IV1350.controller.Controller;
import IV1350.integration.ItemNotFoundException;
import IV1350.integration.ConnectionFailException;
import IV1350.model.Logger;
import IV1350.model.TotalRevenueFileOutput;

import java.time.LocalDateTime;

/**
 * A representation of the graphical interface that the cashier will have.
 */
public class View {
    private Controller controller;
    private Logger logger;

    /**
     * Initializes the {@Controller} that is the core of this program. 
     * 
     * @param controller 
     */
    public View(Controller controller){
        this.controller = controller;
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        totalRevenueView.setLogger(new TotalRevenueFileOutput());
        controller.addSaleObserver(totalRevenueView);
    }

    /**
     * A simulation of a {@Sale} with all necessary method calls.
     */
    public void sampleExecution(){
        System.out.println("Cashier starts new sale.\n");
        controller.startNewSale();
        System.out.println("Cashier enter items. \n");
        registerItem("1001");
        registerItem("100");
        registerItem("111");
        registerItem("1001");
        registerItem("1001");
        registerItem("1002");
        registerItem("1002");
        registerItem("1003");
        registerItem("1003");
        registerItem("1004");
        registerItem("1001");
        registerItem("1004");
        registerItem("1003");
        registerItem("1004");
        registerItem("1001");
        registerItem("1004");
        String out = controller.endSale();
        System.out.println(out);
        controller.pay(200);
        
        System.out.println("Cashier starts new sale.\n");
        controller.startNewSale();
        System.out.println("Cashier enter items. \n");
        registerItem("1001");
        registerItem("1001");
        registerItem("1001");
        registerItem("1002");
        registerItem("1002");
        registerItem("1003");
        registerItem("1003");
        registerItem("1004");
        registerItem("1001");
        registerItem("1004");
        registerItem("1003");
        registerItem("1004");
        registerItem("1001");
        registerItem("1004");
        out = controller.endSale();
        System.out.println(out);
        controller.pay(200);
        
        }
    
    private void registerItem(String itemIdentifier) {
        try {
            String out = controller.registerItem(itemIdentifier);
            System.out.println(out);
        } catch (ItemNotFoundException itemNotFoundExc) {
            handleException("Item with the identifier identifier " + itemIdentifier +  " does not exist in inventory, please try again.", itemNotFoundExc);
        } catch (ConnectionFailException ConnectionFailExc) {
            handleException("Could not connect to the database", ConnectionFailExc);
        }
    }
    
    private void handleException(String message, Exception exception){
        showErrorMsg(message);
        logException(exception);
    }
    
    private void showErrorMsg(String msg) {
        StringBuilder builder = new StringBuilder();
        builder.append("**** NOTIFICATION CASHIER ****");
        builder.append("\n");
        builder.append(getDateOfToday());
        builder.append(", ERROR: ");
        builder.append(msg);
        builder.append("\n");
        builder.append("******************************");
        builder.append("\n");
        System.out.println(builder.toString());
    }

    private String getDateOfToday(){
        LocalDateTime now = LocalDateTime.now();
        return now.toLocalDate().toString();
    }
    
    private void logException(Exception exc) {
        StringBuilder builder = new StringBuilder();
        builder.append("************ LOG *************");
        builder.append("\n");
        builder.append(getDateOfToday());
        builder.append(", Exception was thrown: ");
        builder.append(exc.getMessage());
        System.out.println(builder.toString());
        System.out.println("******************************" + "\n");
    }
}