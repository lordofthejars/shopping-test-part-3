package org.acme;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Invoice extends PanacheEntity {

    @OneToOne
    @JoinColumn(name = "cart_id")
    public ShoppingBasket shoppingBasket;
    
    public double total;

    public static Invoice findInvoiceByTransaction(long id) {
        return find("shoppingBasket.transactionId", id).firstResult();
    }

}