package org.acme;

import java.time.Duration;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.annotations.SseElementType;

import io.smallrye.mutiny.Multi;

@Path("/checkout")
public class ShopResource {

    @Inject
    CheckoutProcess checkoutProcess;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.accepted().build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkout(ShoppingBasket shoppingBasket) {

        Long id = checkoutProcess.checkout(shoppingBasket);

        UriBuilder uriBuilder = UriBuilder.fromResource(ShopResource.class);
        uriBuilder.path(Long.toString(id));

        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    @SseElementType("text/plain")
    public Multi<Long> totalPurchases() {
        return checkoutProcess.totalCheckouts();
    }
}