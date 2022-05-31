package com.jun.juncw.ent;

import com.jun.juncw.ent.Book;
import com.jun.juncw.ent.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-05-11T15:48:33")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Date> dateCreated;
    public static volatile SingularAttribute<Orders, Double> totalPrice;
    public static volatile SingularAttribute<Orders, Long> customerID;
    public static volatile SingularAttribute<Orders, Long> id;
    public static volatile ListAttribute<Orders, Book> orderItems;
    public static volatile SingularAttribute<Orders, String> status;
    public static volatile SingularAttribute<Orders, Customer> customer;

}