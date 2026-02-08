/* package com.example.POS_System.view;

import com.example.POS_System.controller.Controller;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void executeSale() {
        controller.startNewSale();
        controller.enterItem("001");
        controller.enterItem("002");
        controller.enterItem("002");  // Alternative flow 3-4b
        controller.endSale();
        controller.logSale();
        controller.printReceipt();
    }
}
 */

package com.example.POS_System.view;

import java.io.IOException;

import com.example.POS_System.controller.Controller;
import com.example.POS_System.exceptions.DatabaseFailureException;
import com.example.POS_System.exceptions.ItemNotFoundException;
import com.example.POS_System.integration.AccountingSystem;
import com.example.POS_System.integration.InventorySystem;
import com.example.POS_System.model.Item;
import com.example.POS_System.model.Receipt;
import com.example.POS_System.model.Sale;
import com.example.POS_System.util.ErrorFileLogger;


public class View {
    private Controller controller;
    private ErrorFileLogger errorLogger;


    public View(Controller controller) throws IOException {
        this.controller = controller;
        this.errorLogger = new ErrorFileLogger();
    }


    public void sampleRun() {
        // Starting a new sale
        System.out.println("Starting a new sale...");
        controller.startNewSale();

        //Observer logic
       
        try { 
            controller.addSaleObserver(new TotalRevenueView());
            controller.addSaleObserver(new TotalRevenueFileOutput());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Could not initialize revenue logging.");
            errorLogger.log(e);
        }

        // Adding items to the sale
        addItemToSale("001");
        addItemToSale("002");
        addItemToSale("002");  // Simulate alternative flow 3-4b

        //To trigger an exception
        addItemToSale("999"); // Simulates database failure
        addItemToSale("003"); // Simulate item not found failure. 


        // Ending the sale
        //controller.endSale();
        double totalPrice = controller.endSale();
        System.out.println("Total price: " + totalPrice);

        
        // Amount paid 
        String change = controller.pay(100.0);
        System.out.println("Payment registered");
        System.out.println("Change: " + change);

        //update inventory here.
        // 
        try {
            controller.updateInventory();
        } 
        catch (ItemNotFoundException e) {
            System.out.println("Inventory update failed. Item not found.");
            errorLogger.log(e);
        }

        //Log sale After inventory update.
        System.out.println("Logging sale...");
        controller.logSale();
        System.out.println("Sale logged successfully.");
        // Printing the receipt
        //controller.printReceipt();
        Receipt receipt = controller.getReceipt();
        receipt.printReceipt();

    }

    /* private void addItemToSale(String itemId) {
        //controller.enterItem(itemId);
        Item item = controller.enterItem(itemId);
        if(item != null ){
            System.out.println("Item added: " + item.getDescription());
        }else {
            System.out.println("Invalid item identifier.");
        }

    } */

        /* private void addItemToSale(String itemId) {
    try {
        Item item = controller.enterItem(itemId);
        System.out.println("Item added: " + item.getDescription() + " x" +  item.getQuantity() + " " +  item.getPrice());

    } catch (ItemNotFoundException e) {
        System.out.println("Error: Item with identifier "+ itemId + " not found.");
        errorLogger.log(e);

    } catch (DatabaseFailureException e) {
        System.out.println("Error: Database connection failure for item: " + itemId);
        errorLogger.log(e);
    }
} */
    private void addItemToSale(String itemId) {
    try {
        Item item = controller.enterItem(itemId);

        double runningTotal = controller.getRunningTotal();
        double itemTotal = item.getPrice() * item.getQuantity() * (1 + item.getVATRate());

        System.out.println(
            "Item added: " +
            item.getDescription() +
            " x" + item.getQuantity() +
            " | Item total: " + String.format("%.2f", itemTotal) +
            " | Running total: " + String.format("%.2f", runningTotal)
        );

    } catch (ItemNotFoundException e) {
        System.out.println("Error: Item with identifier " + itemId + " not found.");
        errorLogger.log(e);

    } catch (DatabaseFailureException e) {
        System.out.println("Error: Database connection failure for item: " + itemId);
        errorLogger.log(e);
    }
}



    /* public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();
        AccountingSystem accountingSystem = new AccountingSystem();
        Controller controller = new Controller(inventorySystem, accountingSystem);
        View view = new View(controller);

        view.sampleRun();
    }
 */

    public static void main(String[] args) {
    try {
        InventorySystem inventorySystem = new InventorySystem();
        AccountingSystem accountingSystem = new AccountingSystem();
        Controller controller = new Controller(inventorySystem, accountingSystem);
        View view = new View(controller);

        view.sampleRun();

    } catch (IOException e) {
        System.out.println("System error: could not start application.");
        e.printStackTrace();
    }
}

    /* public void executeSale() {
        controller.startNewSale();
        controller.enterItem("001");
        controller.enterItem("002");
        controller.enterItem("002");  // Alternative flow 3-4b
        controller.endSale();
        controller.logSale();
        controller.getReceipt();
    }  */

        public void executeSale() {
    try {
        controller.startNewSale();

        controller.enterItem("001");
        controller.enterItem("002");
        controller.enterItem("002");  // Alternative flow 3–4b

        controller.endSale();
        controller.logSale();
        controller.getReceipt();

    } catch (ItemNotFoundException e) {
        System.out.println("Item not found. Please check the identifier.");
        errorLogger.log(e);

    } catch (DatabaseFailureException e) {
        System.out.println("System error. Please try again later.");
        errorLogger.log(e);
    }
}

    
}
