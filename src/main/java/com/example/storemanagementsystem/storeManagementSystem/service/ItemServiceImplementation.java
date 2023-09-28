package com.example.storemanagementsystem.storeManagementSystem.service;

import com.example.storemanagementsystem.storeManagementSystem.dao.Storedao;
import com.example.storemanagementsystem.storeManagementSystem.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemServiceImplementation implements ItemServiceInterface {

    @Autowired
    public Storedao storedao;
    @Override
    public Item saveItem(Item item) {
        int quantity = Integer.parseInt(item.getQuantity());
        float price = item.getPrice();

        if (quantity < 0) {
            throw new IllegalArgumentException("Invalid item quantity: " + quantity);
        }

        if (price < 0) {
            throw new IllegalArgumentException("Invalid item price: " + price);
        }

        return storedao.save(item);
    }

    @Override
    public List<Item> findAllItems() {
        return storedao.findAll();
    }

    @Override
    public Item findItemById(Integer id) {
        return null;
    }

    @Override
    public Item updateItemById(Integer id, String destination) throws Exception {
        return null;
    }

    public List<Item> findItemByItemName(String itemName) {
        return storedao.findByName(itemName);
    }

    @Override
    @Transactional
    public boolean deleteItemByItemName(String itemName) {
        List<Item> itemsToDelete = storedao.findByName(itemName);

        if (!itemsToDelete.isEmpty()) {
            storedao.deleteByName(itemName);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteItemById(Integer id)
    {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid item ID: " + id);
        }

        Item item = storedao.findById(id).orElse(null);

        if (item == null) {
            throw new NoSuchElementException("Item with ID " + id + " does not exist.");
        }

        storedao.delete(item);
        return true;
    }


}
