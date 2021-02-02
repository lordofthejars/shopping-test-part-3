package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class DeliveryService {
    
    private List<ShoppingBasket> processedItems = new ArrayList<>();

    @Incoming("delivery-warehouse")
    public void deliverPurchase(String shoppingBasket) {
        final ShoppingBasket cart = JsonbBuilder.create().fromJson(shoppingBasket, ShoppingBasket.class);
        processedItems.add(cart);
        System.out.println(String.format("Sending to %s the following items %s", cart.address.toString(), cart.toString()));

    }

    public List<ShoppingBasket> getProcessedItems() {
        return processedItems;
    }

}
