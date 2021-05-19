package IV1350.startup;

import IV1350.controller.Controller;
import IV1350.integration.Printer;
import IV1350.integration.SystemCreator;
import IV1350.integration.Register;
import IV1350.model.TotalRevenueFileOutput;
import IV1350.view.View;

/**
 * The main method of the program.
 */
public class Main {

    /**
    * Application start
    *
    * @param args The method does not take any initial
    *             input on startup. 
    */
    public static void main(String[] args) {

        SystemCreator systemCreator = new SystemCreator();
        Printer printer = new Printer();
        Register register = new Register();
        Controller controller = new Controller(printer, register, systemCreator);
        View view = new View(controller);

        view.sampleExecution();

    }

}