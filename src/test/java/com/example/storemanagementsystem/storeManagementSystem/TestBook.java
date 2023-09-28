package com.example.storemanagementsystem.storeManagementSystem;

import com.example.storemanagementsystem.storeManagementSystem.model.Book;
import com.example.storemanagementsystem.storeManagementSystem.service.BookServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestBook {
    @Autowired
    private BookServiceInterface bookServiceInterface;

    @Test
    public void testSave(){
        Book book = new Book();
        book.setId(1);
        book.setAuthorFirstName("Michaella");
        book.setAuthorLastName("Marie");
        book.setTitle("Economics");
        book.setAuthorEmail("marie@gmail.com");

        Book books = bookServiceInterface.saveBook(book);
        assertNotNull(books);
    }

    @Test
    public void TestFindAll(){
        List<Book> books = bookServiceInterface.findAllBooks();
        assertEquals(1, books.size());
    }

    @Test
    public void TestFindById(){
        Book books = bookServiceInterface.findBookById(1);
        assertEquals("Economics", books.getTitle());
    }

    @Test
    public void testUpdateByName() throws Exception{
        Book updatedBook = bookServiceInterface.updateBookById (1, "Maths");
        assertEquals("Maths", updatedBook.getTitle());
    }

    @Test
    public void testDelete(){
        bookServiceInterface.deleteBookById(1);
        Book books = bookServiceInterface.findBookById(1);
        assertNull(books);
    }

    @Test
    public void TestFindByTitle(){ // Test find a book by title
        List<Book> booksTitle =  bookServiceInterface.findBookByTitle("Victor");
        assertEquals(1,booksTitle.size());
        assertEquals("email",booksTitle.get(0).getAuthorEmail());

    }

}
