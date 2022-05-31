package com.jun.juncw.ent;

import com.jun.juncw.ent.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-05-10T20:26:29")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> city;
    public static volatile ListAttribute<Address, Person> residents;
    public static volatile SingularAttribute<Address, Long> id;

}