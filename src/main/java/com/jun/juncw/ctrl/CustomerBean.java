/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.jun.juncw.ctrl;

import com.jun.juncw.bus.BusinessException;
import com.jun.juncw.bus.CustomerService;
import com.jun.juncw.ent.Customer;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller for handling login and registration
 * @author Jun Chia
 */
@Named(value = "customerBean")
@RequestScoped
public class CustomerBean {

    /**
     * Creates a new instance of UserBean
     */
    public CustomerBean() {
    }
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext externalContext = context.getExternalContext();
    HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    

     
    @Inject
    private LoginBean loginBean;
    
    @EJB
    private CustomerService cs;
    
    /**
     * Registers user using information they typed in
     * @return redirect to login page
     * @throws IOException 
     */
    public String doRegister() throws IOException{
        cs.register(firstName, lastName, email, password);
        FacesMessage msg = new FacesMessage("Successfully registered");
        FacesContext.getCurrentInstance().addMessage("", msg);
        externalContext.redirect("login.xhtml");
        return "login.xhtml";
    }
    
    /**
     * Log in user with inputted information and checks with database
     * @return error or redirect to home page
     * @throws IOException 
     */
    public String login() throws IOException{
        String dbFirstName = "";
        String dbLastName = "";
        String dbEmail = "";
        String dbPassword = "";
        Customer customer = null;
        
        try {
            customer = cs.login(email,password);
            dbEmail = customer.getEmail();
            dbPassword = customer.getPassword();
            dbFirstName = customer.getFirstName();
            dbLastName = customer.getLastName();
            
        }catch (BusinessException ex) {
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("", msg);
        }catch (NullPointerException ex){
            FacesMessage msg = new FacesMessage("Cannot find user" + ex);
            FacesContext.getCurrentInstance().addMessage("", msg);
        }
        
        
        if(email.equals(dbEmail) && password.equals(dbPassword)){
            request.getSession().setAttribute("customer", customer);
            externalContext.redirect("start.xhtml");
            return "start.xhtml";
        }else{
            FacesMessage msg = new FacesMessage("Email or Password are incorrect");
            FacesContext.getCurrentInstance().addMessage("", msg);
            return "";
        }
        
    }
    
    
}
