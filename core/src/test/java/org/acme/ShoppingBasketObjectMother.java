package org.acme;

import java.util.Arrays;

public class ShoppingBasketObjectMother {

    static final Long TRANSACTION_ID = 12345L;

    public static ShoppingBasket theHobbiBasket() {
        
        final Customer customer = new Customer();
        customer.city = "Barcelona";
        customer.country = "Spain";
        customer.name = "Alex";

        Book book = new Book();
        book.name = "The Hobbit";
        book.price = 15;

        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.address = customer;
        shoppingBasket.cart = Arrays.asList(book);
        shoppingBasket.transactionId = TRANSACTION_ID;

        return shoppingBasket;
    }
    
}