/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun.juncw.pers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jun.juncw.ent.Book;
import com.jun.juncw.ent.Orders;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Handles all database query for orders
 * @author Jun Hui Chia
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> {

    @PersistenceContext(unitName = "com.Jun_JunCW_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }
    
    /**
     * Finds order using customer ID
     * @param id
     * @return List of Orders | null
     */
    public List<Orders> findByCustomerID (Long id){
        try{
            Query q = em.createNamedQuery("Orders.findByCustomerID");
            q.setParameter("customerID", id);
            List<Orders> o = (List<Orders>) q.getResultList();
            return o; 
        }catch(NoResultException ex){
            return null;
        }
    }
    
    /**
     * Finds order using order ID
     * @param id
     * @return Order | null
     */
    public Orders findSingleByOrderID( Long id) {
        try{
            Query q = em.createNamedQuery("Orders.findByOrderID");
            q.setParameter("orderID", id);
            Orders o = (Orders) q.getSingleResult();
            return o; 
        }catch(NoResultException ex){
            return null;
        }
    }
}
