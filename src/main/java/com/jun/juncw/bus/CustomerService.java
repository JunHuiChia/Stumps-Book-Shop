/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.jun.juncw.bus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.jun.juncw.ent.Customer;
import com.jun.juncw.pers.CustomerFacade;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

/**
 *  Handles login, registration and retrieving customers by their email
 * @author Jun Chia
 */
@Stateless
public class CustomerService {

    @EJB
    private CustomerFacade cf;
    
    /**
     * Register user and save their details to database
     * @param fName
     * @param lName
     * @param email
     * @param password
     * @return 
     */
    public String register(String fName, String lName, String email, String password){
        Customer c = new Customer();
        c.setFirstName(fName);
        c.setLastName(lName);
        c.setEmail(email);
        c.setPassword(password);
        c.setIsManager(false);
        System.out.println(c);
        cf.create(c);
        return "";
    }
    
    /**
     * Logs the user in by retrieving their details from database
     * @param email
     * @param password
     * @return customer Object
     * @throws BusinessException 
     */
    public Customer login(String email, String password) throws BusinessException{
        Customer co = new Customer();
        
        try{
            co = cf.login(email, password);
            
        }catch(NoResultException ex){
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("", msg);
        }
        
        return co;
    }
    
    /**
     * Find specific user using their email
     * @param email
     * @return customer object
     */
    public Customer findByEmail(String email){
        Customer c = new Customer();
        if (email == null){
            throw new Error("findByEmail : No email specified");
        }
        try{
            c = cf.findByEmail(email);
            
        }catch(NoResultException ex){
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("", msg);
        }
        
        return c;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
