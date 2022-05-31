/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.jun.juncw.ctrl;

import com.jun.juncw.bus.OrderService;
import com.jun.juncw.ent.Book;
import com.jun.juncw.ent.Customer;
import com.jun.juncw.ent.Orders;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller for handling orders and what the user sees
 * @author Jun Chia
 */
@Named(value = "orderBean")
@SessionScoped
public class OrderBean implements Serializable {

    /**
     * Creates a new instance of CartBean
     */
    public OrderBean() {
    }
    
    private List<Orders> orders = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<String> status = Arrays.asList("ordered","pending","dispatched","cancelled");
    private String newStatus;
    
    @EJB
    private OrderService os;
    
    /**
     * Get list of orders
     * @return list of orders
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     * Set list of orders
     * @param orders 
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     * Get status of order
     * @return 
     */
    public List<String> getStatus() {
        return status;
    }

    /**
     * Set status of order
     * @param status 
     */
    public void setStatus(List<String> status) {
        this.status = status;
    }

    /**
     * get new status of order
     * @return 
     */
    public String getNewStatus() {
        return newStatus;
    }

    /**
     * set new status of order
     * @param newStatus 
     */
    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
    
    
    /**
     * Get customer's orders by retrieving from database
     * @return List of orders
     */
    public String loadOrders(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        Customer c = (Customer) request.getSession().getAttribute("customer");
        if(c != null){
            orders = os.findByID(c.getId());
            return "";
        }else{
            return "start.xhtml";
        }
    }
    
    /**
     * Get all orders from database
     */
    public void allOrders(){
        orders = os.findAll();
    }
    
    /**
     * Changes the status of selected order
     * @param order
     * @return admin page
     */
    public String changeStatus(Orders order){
        System.out.println("orderbean new status: " + newStatus);
        os.changeStatus(order, newStatus);
        return "admin.xhtml";
    }
        
}
