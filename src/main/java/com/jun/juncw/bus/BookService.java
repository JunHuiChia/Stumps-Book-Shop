/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun.juncw.bus;

import com.jun.juncw.ent.Book;
import com.jun.juncw.pers.BookFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Handles searching for books in the database
 * @author Jun Chia
 */
@Stateless
public class BookService {
    @EJB
    private BookFacade bf;
    
    /**
     * Adds book to database given Book object
     * @param b 
     */
    public void addBook(Book b) {
        bf.create(b);
    }   
    /**
     * Finds all books in database
     * @return all books
     */
    public List<Book> findAllBooks(){
        return bf.findAll();
    }
    
    /**
     * Find specific book 
     * @param id
     * @return book
     */
    public Book findBookByID(Long id){
        return bf.findBookByID(id);
    }
}
