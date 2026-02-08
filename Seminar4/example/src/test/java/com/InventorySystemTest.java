package com;

import com.example.POS_System.exceptions.DatabaseFailureException;
import com.example.POS_System.exceptions.ItemNotFoundException;
import com.example.POS_System.integration.InventorySystem;
import com.example.POS_System.model.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventorySystemTest {

    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
    }

    @Test
    public void testInitialInventoryContainsDefaultItems() throws Exception {
        assertEquals(2, inventorySystem.getItemCount());
        assertNotNull(inventorySystem.getItem("001"));
        assertNotNull(inventorySystem.getItem("002"));
    }

    @Test
    public void testAddItemIncreasesItemCount() {
        Item butter = new Item("003", "Butter", 30, 0.12);
        inventorySystem.addItem(butter);
        assertEquals(3, inventorySystem.getItemCount());
    }

    @Test
    public void testRemoveItemDecreasesItemCount() {
        inventorySystem.removeItem("001");
        assertEquals(1, inventorySystem.getItemCount());
    }

    @Test
    public void testGetItem_ItemNotFoundException() {
        ItemNotFoundException exception =
            assertThrows(ItemNotFoundException.class, () -> {
                inventorySystem.getItem("003");
            });

        assertTrue(exception.getMessage().contains("003"));
    }

    @Test
    public void testGetItem_DatabaseFailureException() {
        DatabaseFailureException exception =
            assertThrows(DatabaseFailureException.class, () -> {
                inventorySystem.getItem("999");
            });

        assertTrue(exception.getMessage().contains("Database"));
    }
}

