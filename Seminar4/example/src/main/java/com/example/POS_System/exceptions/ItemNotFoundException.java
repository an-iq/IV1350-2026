package com.example.POS_System.exceptions;

/**
 * Thrown when an item with a specified identifier is not found in the inventory.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemId) {
        super("Item with identifier " + itemId + " was not found.");
    }
}

