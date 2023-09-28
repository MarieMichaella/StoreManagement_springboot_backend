package com.example.storemanagementsystem.storeManagementSystem.service;

import com.example.storemanagementsystem.storeManagementSystem.model.Item;

import java.util.List;

public interface ItemServiceInterface {
    public Item saveItem(Item item);

    List<Item> findAllItems();
    Item findItemById(Integer id);
    Item updateItemById(Integer id, String title) throws Exception;

    List<Item> findItemByItemName(String itemName);

    boolean deleteItemByItemName(String itemName);



    boolean deleteItemById(Integer id);
}
