package com.example.storemanagementsystem.storeManagementSystem;

import com.example.storemanagementsystem.storeManagementSystem.model.Item;
import com.example.storemanagementsystem.storeManagementSystem.service.ItemServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestCreateUpdate23574 {

    @Autowired
    private ItemServiceInterface itemServiceInterface;

    @Test
    public void testSave() {
        Item item = new Item();
        item.setItemId(1);
        item.setItemName("SamsungPhone");
        item.setDescription("Best Phones");
        item.setCategory("Phones");
        String priceString = "19000";
        float priceFloat = Float.parseFloat(priceString);
        item.setPrice(priceFloat);
        item.setQuantity("3");

        Item item1 = new Item();
        item1.setItemId(2);
        item1.setItemName("ApplePhone");
        item1.setDescription("Best Phones");
        item1.setCategory("Phones");
        String price1String = "20000";
        float price1Float = Float.parseFloat(priceString);
        item1.setPrice(priceFloat);
        item1.setQuantity("3");

        Item item2 = new Item();
        item2.setItemId(3);
        item2.setItemName("ApplePhone");
        item2.setDescription("Best Phones");
        item2.setCategory("Phones");
        String price2String = "20000";
        float price2Float = Float.parseFloat(priceString);
        item2.setPrice(priceFloat);
        item2.setQuantity("3");

        Item items = itemServiceInterface.saveItem(item);
        Item items1 = itemServiceInterface.saveItem(item1);
        Item items2 = itemServiceInterface.saveItem(item2);
        assertNotNull(items);
        assertNotNull(items1);
        assertNotNull(items2);


    }


    @Test
    public void testSaveNegativeMissingFields() {
        Item item = new Item();
        assertThrows(Exception.class, () -> itemServiceInterface.saveItem(item));
    }

    @Test
    public void testSaveNegativeInvalidPrice() {
        Item item = new Item();
        item.setItemName("SamsungPhone");
        item.setDescription("Best Phones");
        item.setCategory("Phones");
        item.setPrice(-100.0f);  // Invalid negative price
        item.setQuantity("3");

        assertThrows(Exception.class, () -> itemServiceInterface.saveItem(item));
    }






    @Test
    public void testSaveNegativeMissingQuantity() {
        Item item = new Item();
        item.setItemName("SamsungPhone");
        item.setDescription("Best Phones");
        item.setCategory("Phones");
        item.setPrice(19000.0f);

        assertThrows(Exception.class, () -> itemServiceInterface.saveItem(item));
    }

    @Test
    public void testDelete(){
        itemServiceInterface.deleteItemById(1);
        Item item = itemServiceInterface.findItemById(1);
        assertNull(item);
    }

    @Test
    public void testRemoveItemAndUpdateTotal() {
        List<Item> initialItems = itemServiceInterface.findAllItems();
        int initialTotal = initialItems.size();

        // Remove an item, e.g., item with ID 1
        itemServiceInterface.deleteItemById(1);

        List<Item> remainingItems = itemServiceInterface.findAllItems();
        int newTotal = remainingItems.size();

        assertEquals(initialTotal - 1, newTotal);

        System.out.println("Remaining items in the store:");
        for (Item item : remainingItems) {
            System.out.println(item.getItemName());
        }
    }

    @Test
    public void testRemoveItemAndUpdateTotal1() {
        List<Item> initialItems = itemServiceInterface.findAllItems();
        int initialTotal = initialItems.size();

        itemServiceInterface.deleteItemById(2);

        List<Item> remainingItems = itemServiceInterface.findAllItems();
        int newTotal = remainingItems.size();

        assertEquals(initialTotal - 1, newTotal);


        int totalQuantity = 0;
        for (Item item : remainingItems) {
            totalQuantity += Integer.parseInt(item.getQuantity());
        }


        System.out.println("Updated Total Quantity: " + totalQuantity);
        System.out.println("Remaining items in the store:");
        for (Item item : remainingItems) {
            System.out.println(item.getItemName());
        }
    }

    @Test
    public void testNegativeRemoveNonExistentItem() {
        assertThrows(NoSuchElementException.class, () -> itemServiceInterface.deleteItemById(999));
    }

    @Test
    public void testRemoveItemWithInvalidID() {
        assertThrows(IllegalArgumentException.class, () -> itemServiceInterface.deleteItemById(-1));
    }




    @Test
    public void testAddItemAndUpdateTotal() {
        List<Item> initialItems = itemServiceInterface.findAllItems();
        int initialTotal = initialItems.size();

        Item newItem = new Item();
        newItem.setItemId(9);
        newItem.setItemName("NewPhone");
        newItem.setDescription("Brand new phone");
        newItem.setCategory("Phones");
        newItem.setPrice(25000.0f);
        newItem.setQuantity("5");

        itemServiceInterface.saveItem(newItem);

        List<Item> updatedItems = itemServiceInterface.findAllItems();
        int updatedTotal = updatedItems.size();

        assertEquals(initialTotal + 1, updatedTotal);

        System.out.println("Updated items in the store:");
        for (Item item : updatedItems) {
            System.out.println(item.getItemName());
        }
    }

    @Test
    public void testAddItemAndUpdateTotalQuantity() {
        List<Item> initialItems = itemServiceInterface.findAllItems();
        int initialTotal = initialItems.size();

        Item newItem = new Item();
        newItem.setItemId(6);
        newItem.setItemName("Huwaei");
        newItem.setDescription("Brand new phone");
        newItem.setCategory("Phones");
        newItem.setPrice(25000.0f);
        newItem.setQuantity("5");


        itemServiceInterface.saveItem(newItem);


        int initialTotalQuantity = calculateTotalQuantity(initialItems);


        List<Item> updatedItems = itemServiceInterface.findAllItems();
        int updatedTotalQuantity = calculateTotalQuantity(updatedItems);

        assertEquals(initialTotalQuantity + Integer.parseInt(newItem.getQuantity()), updatedTotalQuantity);

        System.out.println("Updated Total Quantity: " + updatedTotalQuantity);
        System.out.println("Updated items in the store:");
        for (Item item : updatedItems) {
            System.out.println(item.getItemName());
        }
    }

    private int calculateTotalQuantity(List<Item> items) {
        int totalQuantity = 0;
        for (Item item : items) {
            totalQuantity += Integer.parseInt(item.getQuantity());
        }
        return totalQuantity;
    }



    @Test
    public void testAddItemAndUpdateTotalQuantityAndPrices() {
        List<Item> initialItems = itemServiceInterface.findAllItems();
        int initialTotal = initialItems.size();

        Item newItem = new Item();
        newItem.setItemId(7);
        newItem.setItemName("Oppo");
        newItem.setDescription("Brand new phone");
        newItem.setCategory("Phones");
        newItem.setPrice(25000.0f);
        newItem.setQuantity("5");

        itemServiceInterface.saveItem(newItem);


        float initialTotalPrice = calculateTotalPrice(initialItems);

        List<Item> updatedItems = itemServiceInterface.findAllItems();


        float updatedTotalPrice = calculateTotalPrice(updatedItems);



        // Verify that the total price has been correctly updated
        assertEquals(initialTotalPrice + (Float.parseFloat(newItem.getQuantity()) * newItem.getPrice()), updatedTotalPrice);

        System.out.println("Updated Total Price: " + updatedTotalPrice);
    }



    private float calculateTotalPrice(List<Item> items) {
        float totalPrice = 0.0f;
        for (Item item : items) {
            totalPrice += Float.parseFloat(item.getQuantity()) * item.getPrice();
        }
        return totalPrice;
    }

    @Test
    public void testUpdateTotalNegativeInvalidItemQuantity() {
        List<Item> initialItems = itemServiceInterface.findAllItems();
        int initialTotalQuantity = calculateTotalQuantity(initialItems);

        Item newItem = new Item();
        newItem.setItemId(4);
        newItem.setItemName("NewPhone");
        newItem.setDescription("Brand new phone");
        newItem.setCategory("Phones");
        newItem.setPrice(25000.0f);
        newItem.setQuantity("-10");


        assertThrows(IllegalArgumentException.class, () -> itemServiceInterface.saveItem(newItem));


        List<Item> updatedItems = itemServiceInterface.findAllItems();
        int updatedTotalQuantity = calculateTotalQuantity(updatedItems);
        assertEquals(initialTotalQuantity, updatedTotalQuantity);
    }

    @Test
    public void testUpdateTotalNegativeInvalidItemPrice() {
        List<Item> initialItems = itemServiceInterface.findAllItems();
        int initialTotalQuantity = calculateTotalQuantity(initialItems);

        Item newItem = new Item();
        newItem.setItemId(4);
        newItem.setItemName("NewPhone");
        newItem.setDescription("Brand new phone");
        newItem.setCategory("Phones");
        newItem.setPrice(-25000.0f);
        newItem.setQuantity("5");


        assertThrows(IllegalArgumentException.class, () ->  itemServiceInterface.saveItem(newItem));


        List<Item> updatedItems = itemServiceInterface.findAllItems();
        int updatedTotalQuantity = calculateTotalQuantity(updatedItems);
        assertEquals(initialTotalQuantity, updatedTotalQuantity);
    }





}
