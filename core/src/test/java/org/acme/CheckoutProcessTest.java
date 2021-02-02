package org.acme;

import static org.assertj.core.api.Assertions.assertThat;


import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.connectors.InMemorySink;


@QuarkusTest
@Transactional
public class CheckoutProcessTest {

    @Inject
    CheckoutProcess checkoutProcess;

    @Inject @Any
    InMemoryConnector connector;

    @Test
    public void should_apply_checkout() {
        ShoppingBasket shoppingBasket = ShoppingBasketObjectMother.theHobbiBasket();
        checkoutProcess.checkout(shoppingBasket);

        Invoice invoce = Invoice.findInvoiceByTransaction(ShoppingBasketObjectMother.TRANSACTION_ID);

        assertThat(invoce.id).isNotNull();

        InMemorySink<String> queue = connector.sink("delivery"); 
        String payload = queue.received().get(0).getPayload();

        final ShoppingBasket cart = JsonbBuilder.create().fromJson(payload, ShoppingBasket.class);
        assertThat(cart.transactionId).isEqualTo(ShoppingBasketObjectMother.TRANSACTION_ID);

    }

    @BeforeEach
    public void clean() {
        Invoice.deleteAll();
        ShoppingBasket.deleteAll();
    }

    @Test
    public void should_stream_total_checkouts() {

        ShoppingBasket shoppingBasket = ShoppingBasketObjectMother.theHobbiBasket();
        checkoutProcess.checkout(shoppingBasket);

        checkoutProcess.totalCheckouts()
            .transform().byTakingFirstItems(1)
            .subscribe().withSubscriber(AssertSubscriber.create(1))
            .await()
            .assertItems(1L)
            .assertCompleted();
    }

}