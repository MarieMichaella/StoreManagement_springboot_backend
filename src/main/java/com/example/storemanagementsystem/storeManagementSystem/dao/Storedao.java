package com.example.storemanagementsystem.storeManagementSystem.dao;

import com.example.storemanagementsystem.storeManagementSystem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Storedao extends JpaRepository<Item, Integer> {

    @Query("SELECT item FROM Item item WHERE item.itemName = :itemName")
    List<Item> findByName(String itemName);

    @Modifying
    @Query("DELETE FROM Item item WHERE item.itemName = :itemName")
    void deleteByName(String itemName);
}
