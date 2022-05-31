/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun.juncw.bus;

import com.jun.juncw.ent.Book;
import com.jun.juncw.ent.Customer;
import com.jun.juncw.ent.Orders;
import com.jun.juncw.pers.BookFacade;
import com.jun.juncw.pers.CustomerFacade;
import com.jun.juncw.pers.OrdersFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Handles creating orders
 * @author Jun Chia
 */
@Stateless
public class CartService {
    @EJB
    private OrdersFacade of;
    @EJB
    private BookFacade bf;
    @EJB
    private CustomerFacade cf;
    
    /**
     * Creates new order and save to database
     * @param c
     * @param orderItems
     * @param totalPrice 
     */
    public void createOrder(Customer c, List<Book> orderItems, Double totalPrice){
        Orders newOrder = new Orders();
        newOrder.setCustomer(c);
        newOrder.setCustomerID(c.getId());
        newOrder.setDateCreated(new Date());
        newOrder.setOrderItems(orderItems);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setStatus("pending");
        of.create(newOrder);
    }
    
}
