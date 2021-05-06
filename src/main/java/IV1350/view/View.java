package IV1350.view;

import IV1350.controller.Controller;

/**
 * A representation of the graphical interface that the cashier will have.
 */
public class View {
    private Controller controller;

    /**
     * Initializes the {@Controller} that is the core of this program. 
     * 
     * @param controller 
     */
    public View(Controller controller){
        this.controller = controller;
    }

    /**
     * A simulation of a {@Sale} with all necessary method calls.
     */
    public void sampleExecution(){
        System.out.println("Cashier starts new sale.\n");
        controller.startNewSale();
        System.out.println("Cashier enter items. \n");
        String out = controller.registerItem("1001");
        System.out.println(out);
        out = controller.registerItem("100");
        System.out.println(out);
        out = controller.registerItem("1001");
        System.out.println(out);
        out = controller.registerItem("1001");
        System.out.println(out);
        out = controller.registerItem("1002");
        System.out.println(out);
        out = controller.registerItem("1002");
        System.out.println(out);
        out = controller.registerItem("1003");
        System.out.println(out);
        out = controller.registerItem("1003");
        System.out.println(out);
        out = controller.registerItem("1004");
        System.out.println(out);
        out = controller.registerItem("1001");
        System.out.println(out);
        out = controller.registerItem("1004");
        System.out.println(out);
        out = controller.registerItem("1003");
        System.out.println(out);
        out = controller.registerItem("1004");
        System.out.println(out);
        out = controller.registerItem("1001");
        System.out.println(out);
        out = controller.registerItem("1004");
        System.out.println(out);
        out = controller.endSale();
        System.out.println(out);
        controller.pay(200);
        
        }
}