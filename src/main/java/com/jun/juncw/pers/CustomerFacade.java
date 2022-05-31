/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun.juncw.pers;

import com.jun.juncw.ent.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Handles all database querying for Customers
 * @author Jun Chia
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "com.Jun_JunCW_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    /**
     * Logs the user in with information given
     * @param email
     * @param password
     * @return null | Customer Object
     * @throws NoResultException 
     */
    public Customer login(String email, String password) throws NoResultException{
        
//        Query q = em.createNamedQuery("Customer.findByEmail");
//        q.setParameter("email", email);
//        return q.getResultList();
        try{
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery cq = cb.createQuery();
//            Root<Customer> customer = cq.from(Customer.class);
//            cq.where(cb.equal(customer.get(Customer_.email), email));
//            cq.multiselect(customer.get(Customer_.firstName),customer.get(Customer_.lastName),customer.get(Customer_.email),customer.get(Customer_.password));
//            TypedQuery<Object[]> q = em.createQuery(cq);
            Query q = em.createNamedQuery("Customer.login");
            q.setParameter("email", email);
            q.setParameter("password", password);
            Customer c = (Customer) q.getSingleResult();
            return c; 
        }catch(NoResultException ex){
            return null;
        }
    }
    
    /**
     * Find customer using email
     * @param email
     * @return null | Customer object
     */
    public Customer findByEmail( String email){
        try{
            Query q = em.createNamedQuery("Customer.findByEmail");
            q.setParameter("email", email);
            Customer c = (Customer) q.getSingleResult();
            return c; 
        }catch(NoResultException ex){
            return null;
        }
    }
}
