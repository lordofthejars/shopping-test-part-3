package org.acme;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Customer extends PanacheEntity {

    // and more and more

    public String name;
    public String city;
    public String country;

    @Override
    public String toString() {
        return "Customer [city=" + city + ", country=" + country + ", name=" + name + "]";
    }
    
}