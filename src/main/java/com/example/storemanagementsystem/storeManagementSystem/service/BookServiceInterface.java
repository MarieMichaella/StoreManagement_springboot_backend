package com.example.storemanagementsystem.storeManagementSystem.service;

import com.example.storemanagementsystem.storeManagementSystem.model.Book;
import com.example.storemanagementsystem.storeManagementSystem.model.Item;

import java.util.List;

public interface BookServiceInterface {
    public Book saveBook(Book book);
    List<Book> findAllBooks();
    Book findBookById(Integer id);
    Book updateBookById(Integer id, String title) throws Exception;
    boolean deleteBookById(Integer id);
    List<Book> findBookByTitle(String title);
}
