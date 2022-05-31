/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.jun.juncw.ctrl;

import com.jun.juncw.bus.BookService;
import com.jun.juncw.ent.Book;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


/**
 * Controller for managing books
 * @author Jun Chia
 */
@Named(value = "bookBean")
@ViewScoped
public class BookBean implements Serializable{

    /**
     * Creates a new instance of BookBean
     */
    public BookBean() {
    }
    
    private List<Book> allBooks = new ArrayList<>();
    private Book currentBook;
    
    private String bookName;
    private String author;
    private String publisher;
    private Integer edition;
    private Integer yop;
    private Double price;
    
    @EJB
    private BookService bs;

    /**
     * Get book name
     * @return book name
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Set new book name
     * @param bookName 
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * Get author name
     * @return author name
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Set new author name
     * @param author 
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get publisher name
     * @return publisher name
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set new publisher name
     * @param publisher 
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get edition of book
     * @return String edition
     */
    public Integer getEdition() {
        return edition;
    }

    /**
     * Set new edition for book
     * @param edition 
     */
    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    /**
     * Get year of publication
     * @return 
     */
    public Integer getYop() {
        return yop;
    }

    /**
     * Set new year of publication
     * @param yop 
     */
    public void setYop(Integer yop) {
        this.yop = yop;
    }

    /**
     * Get price of book
     * @return Double price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set new price for book
     * @param price 
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * Add new book to database
     * @return 
     */
    public String addBook() {
        Book b = new Book();
        b.setAuthor(author);
        b.setBookName(bookName);
        b.setEdition(edition);
        b.setPrice(price);
        b.setYop(yop);
        b.setPublisher(publisher);
        bs.addBook(b);
        return "";
    }

    /**
     * Get the book the user clicks on
     * @return Book
     */
    public Book getCurrentBook() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        Map<String, String> param = externalContext.getRequestParameterMap();
        
        Long bookID = Long.parseLong(param.get("bookID"));
        System.out.println("bookID: " + bookID);
        currentBook = bs.findBookByID(bookID);
        return currentBook;
    }

    /**
     * Set current book the user clicks on
     * @param currentBook 
     */
    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
    }

    
    /**
     * Get all books 
     * @return List of books
     */
    public List<Book> getAllBooks(){
        allBooks = bs.findAllBooks();
        return allBooks;
    }
    
    /**
     * Get the book the user selected
     * @return current Book
     * @throws IOException 
     */
    public Book selectedBook() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        Map<String, String> param = externalContext.getRequestParameterMap();
        
        String bookID = param.get("bookID");
        System.out.println("bookID: " + bookID);
        return currentBook;
    }
}
