/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.jun.juncw.ctrl;

import com.jun.juncw.bus.CustomerService;
import com.jun.juncw.ent.Customer;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for managing status of logged in user and logging users out
 * @author Jun Chia
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    private String firstName = " ";
    private String lastName = " ";
    private String email= "";
    private String password= "";
    private Boolean isLoggedIn = false;
    private Boolean isManager = false;
    
    @EJB
    private CustomerService cs;
    
    
    /**
     * Initialise this bean
     */
    @PostConstruct
    public void init(){
        isLoggedInStatus();
    }
    
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

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }
    
    /**
     * Check session to see if user is logged in or not
     * 
     */
    public void isLoggedInStatus(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        Customer c = (Customer) request.getSession().getAttribute("customer");

        if(c != null){
            Customer customerDetail = cs.findByEmail(c.getEmail());
            firstName = customerDetail.getFirstName();
            lastName = customerDetail.getLastName();
            email = customerDetail.getEmail();
            isManager = customerDetail.getIsManager();
            isLoggedIn = true;
        }else{
            isLoggedIn = false;
        }
    }
    
    /**
     * Deletes current session to log user out and redirect to home page
     * @return redirect to home page
     * @throws IOException 
     */
    public String logOut() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        externalContext.invalidateSession();
        firstName = "";
        lastName = "";
        email = "";
        isLoggedIn = false;
        externalContext.redirect("start.xhtml");
        return "start.xhtml";
    }
    

    
    
}
