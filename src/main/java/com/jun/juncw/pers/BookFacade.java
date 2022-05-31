/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun.juncw.pers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jun.juncw.ent.Book;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Handles all database querying for Books
 * @author Jun Hui Chia
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "com.Jun_JunCW_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    /**
     * Find book using ID
     * @param id
     * @return null if nothing is found | Book object
     */
    public Book findBookByID (Long id){
        try{
            Query q = em.createNamedQuery("Book.findBookByID");
            q.setParameter("id", id);
            Book b = (Book) q.getSingleResult();
            return b; 
        }catch(NoResultException ex){
            return null;
        }
    }
}
