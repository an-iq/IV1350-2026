package com;

import java.io.IOException;

import com.example.POS_System.controller.Controller;
import com.example.POS_System.integration.AccountingSystem;
import com.example.POS_System.integration.InventorySystem;
import com.example.POS_System.view.View;


/**
 * Hello world!
 *
 */
public class Main {
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
}
