/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.jun.juncw.ctrl;

import com.jun.juncw.bus.CartService;
import com.jun.juncw.bus.CustomerService;
import com.jun.juncw.ent.Book;
import com.jun.juncw.ent.Customer;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 * Handles items in cart and displaying to user
 * @author Jun Chia
 */
@Named(value = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

    /**
     * Creates a new instance of CartBean
     */
    public CartBean() {
    }
    
    private List<Book> cartItems = new ArrayList<>();
    private Boolean isEmpty = true;

    /**
     * Check if the cart is empty
     * @return 
     */
    public Boolean getIsEmpty() {
        isEmpty = cartItems.isEmpty();
        return isEmpty;
    }

    /**
     * Set the cart to empty or not
     * @param isEmpty 
     */
    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
    /**
     * Get items in the cart
     * @return List of books
     */
    public List<Book> getCartItems() {
        return cartItems;
    }

    /**
     * Set items in the cart
     * @param cartItems 
     */
    public void setCartItems(List<Book> cartItems) {
        this.cartItems = cartItems;
    }
    
    /**
     * Get the amount of items in cart
     * @return Number of items
     */
    public int getCartTotalItems(){
        return cartItems.size();
    }
    
    /**
     * Get the total price of all items in cart
     * @return total price
     */
    public double getCartTotal(){
        double cartTotal = 0.00;
        List<Book> cart = this.getCartItems();
        for(Book cartItem : cart){
            cartTotal += cartItem.getPrice();
        }
        cartTotal = Math.round(cartTotal * 100.0)/100.0;
        return cartTotal;
    }
    
    /**
     * Add book to cart
     * @param bookToAdd
     * @throws IOException 
     */
    public void addBookToCart(Book bookToAdd) throws IOException{
        System.out.println(bookToAdd);
        List<Book> cart = this.getCartItems();
        cart.add(bookToAdd);
        this.setCartItems(cart);
        FacesContext.getCurrentInstance().getExternalContext().redirect("start.xhtml");

    }
    
    /**
     * delete book from the cart
     * @param bookToDelete
     * @return 
     * @throws IOException 
     */
    public String deleteBookFromCart(Book bookToDelete) throws IOException{
        List<Book> cart = this.getCartItems();
        cart.remove(bookToDelete);
        this.setCartItems(cart);
        FacesContext.getCurrentInstance().getExternalContext().redirect("cart.xhtml");
        return "";
    }
    
    @EJB
    private CartService cs;
    @EJB
    private CustomerService customerS;
    
    /**
     * Lets user checkout their cart
     * @param email
     * @return redirect to orders page
     */
    public String checkoutCart( String email ){
        if(cartItems.isEmpty()){
            return "start.xhtml";
        } else{
            Customer c = customerS.findByEmail(email);
            Double cartTotal = getCartTotal();
            cs.createOrder(c, cartItems, cartTotal);
            cartItems.clear();
            return "orders.xhtml";
        }
    }
        
}
