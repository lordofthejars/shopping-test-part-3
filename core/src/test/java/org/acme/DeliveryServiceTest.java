package org.acme;

import static org.assertj.core.api.Assertions.assertThat;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.connectors.InMemorySource;

@QuarkusTest
public class DeliveryServiceTest {
    
    @Inject
    DeliveryService deliveryService;

    @Inject @Any
    InMemoryConnector connector;

    @Test
    public void should_process_deliveries() {
        InMemorySource<String> deliveries = connector.source("delivery-warehouse");
        String content = JsonbBuilder.create().toJson(ShoppingBasketObjectMother.theHobbiBasket());
        deliveries.send(content);

        assertThat(deliveryService.getProcessedItems()).hasSize(1);
    }

}
