package com.jun.juncw.ent;

import com.jun.juncw.ent.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-05-31T19:32:10")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, Double> price;
    public static volatile SingularAttribute<Book, String> publisher;
    public static volatile SingularAttribute<Book, Integer> edition;
    public static volatile SingularAttribute<Book, Long> id;
    public static volatile SingularAttribute<Book, Integer> yop;
    public static volatile SingularAttribute<Book, String> bookName;
    public static volatile ListAttribute<Book, Orders> order;

}