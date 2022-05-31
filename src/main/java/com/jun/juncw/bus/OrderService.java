/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun.juncw.bus;

import com.jun.juncw.ent.Orders;
import com.jun.juncw.pers.BookFacade;
import com.jun.juncw.pers.CustomerFacade;
import com.jun.juncw.pers.OrdersFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Handles getting orders using customer ID and change status of orders
 * @author Jun Chia
 */
@Stateless
public class OrderService {
    @EJB
    private OrdersFacade of;
    @EJB
    private BookFacade bf;
    @EJB
    private CustomerFacade cf;

    /**
     * Finds order by ID
     * @param customerID
     * @return order
     */
    public List<Orders> findByID(Long customerID){
        return of.findByCustomerID(customerID);
    }
    
    /**
     * Finds all orders
     * @return List of orders
     */
    public List<Orders> findAll(){
        return of.findAll();
    }
    
    /**
     * Changes status of order
     * @param order
     * @param status
     * @return Order
     */
    public Orders changeStatus(Orders order , String status){        
        order = of.edit(order);
        order.setStatus(status);
        order = of.edit(order);
        return order;
    }
    
}
