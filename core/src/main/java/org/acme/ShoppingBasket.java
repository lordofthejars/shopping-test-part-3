package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class ShoppingBasket extends PanacheEntity {

    public Long transactionId;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    public Customer address;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "book_id")
    public List<Book> cart = new ArrayList<>();

    public double calculateTotal() {
        return cart.stream()
            .mapToDouble(b -> b.price)
            .sum();
    }

    @Override
    public String toString() {
        return "ShoppingBasket [cart=" + cart + "]";
    }

}