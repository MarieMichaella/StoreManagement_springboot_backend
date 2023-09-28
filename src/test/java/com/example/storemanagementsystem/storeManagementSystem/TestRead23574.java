package com.example.storemanagementsystem.storeManagementSystem;

import com.example.storemanagementsystem.storeManagementSystem.model.Item;
import com.example.storemanagementsystem.storeManagementSystem.service.ItemServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestRead23574 {
    @Autowired
    private ItemServiceInterface itemServiceInterface;
    @Test
    public void TestFindAll(){
        List<Item> items = itemServiceInterface.findAllItems();
            assertEquals(5, items.size());

    }

    @Test
    public void testFindByName() {

        List<Item> oppoitem = itemServiceInterface.findItemByItemName("Oppo");


        System.out.println("Items :");
        System.out.println("***************************");
        for (Item items : oppoitem) {
            System.out.println("Ticket ID: " + items.getItemId());
            System.out.println("Passenger Name: " + items.getItemName());
            System.out.println("Destination: " + items.getQuantity());
            System.out.println("Source: " + items.getPrice());

            System.out.println("***************************");
        }


        assertEquals(2, oppoitem.size());
        assertEquals("Phones", oppoitem.get(0).getCategory());
    }

    @Test
    public void testDeleteByName_ItemExists_DeletesItem() {
        String itemNameToDelete = "NewPhone";
        assertTrue(itemServiceInterface.deleteItemByItemName(itemNameToDelete));
    }




}
