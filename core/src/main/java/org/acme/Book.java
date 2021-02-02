package org.acme;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Book extends PanacheEntity {
    public String name;
    public double price;

    @Override
    public String toString() {
        return "Book [name=" + name + ", price=" + price + "]";
    }
    
}