package com.example.storemanagementsystem.storeManagementSystem.controller;

import com.example.storemanagementsystem.storeManagementSystem.model.Book;
import com.example.storemanagementsystem.storeManagementSystem.service.BookServiceInterface;
import com.example.storemanagementsystem.storeManagementSystem.service.ItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/id")
public class BookController {

    @Autowired
    private BookServiceInterface bookServiceInterface;
    @PostMapping("/books")
    public ResponseEntity<Book> saveTicket (
            @RequestBody Book books){
        Book savedBook = bookServiceInterface.saveBook(books);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAll(){
        List<Book> books = bookServiceInterface.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book book = bookServiceInterface.findBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Integer id, @RequestBody Book updatedBook) {
        Book book = bookServiceInterface.findBookById(id);

        if (book != null) {

            book.setTitle(updatedBook.getTitle());
            book.setAuthorFirstName(updatedBook.getAuthorFirstName());
            book.setAuthorLastName(updatedBook.getAuthorLastName());
            book.setAuthorEmail(updatedBook.getAuthorEmail());

            Book updated = bookServiceInterface.saveBook(book);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        if (bookServiceInterface.deleteBookById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



