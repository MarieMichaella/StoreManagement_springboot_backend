package com.example.storemanagementsystem.storeManagementSystem.service;

import com.example.storemanagementsystem.storeManagementSystem.dao.Bookdao;
import com.example.storemanagementsystem.storeManagementSystem.model.Book;
import com.example.storemanagementsystem.storeManagementSystem.model.Item;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImplementation implements BookServiceInterface {

    @Autowired
    public Bookdao bookdao;

    @Override
    public Book saveBook(Book book) {
        return bookdao.save(book) ;
    }

    @Override
    public List<Book > findAllBooks() {
        return bookdao.findAll();
    }

    @Override
    public Book findBookById(Integer id) {
        Book book = bookdao.findById(id).orElse(null);

        if(book == null){
            return null;
        } else {
            return book;
        }
    }

    @Override
    public Book updateBookById(Integer id, String title) throws Exception {
        {
            Book book = bookdao.findById(id).orElse(null);

            if (book != null) {
                book.setTitle(title);
                return bookdao.save(book);
            } else {

                throw new ObjectNotFoundException(Book.class, String.valueOf(id));
            }
        }
    }

    @Override
    public boolean deleteBookById(Integer id) {
        Book book = bookdao.findById(id).orElse(null);

        if(book != null){
            bookdao.delete(book);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        return bookdao.findByTitle(title);
    }
}
