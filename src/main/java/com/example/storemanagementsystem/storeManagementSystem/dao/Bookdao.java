package com.example.storemanagementsystem.storeManagementSystem.dao;

import com.example.storemanagementsystem.storeManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Bookdao extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);
}
