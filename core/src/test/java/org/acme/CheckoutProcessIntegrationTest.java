package org.acme;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@TestProfile(IntegrationTestProfile.class)
public class CheckoutProcessIntegrationTest {

    @Test
    public void should_do_a_checkout() throws InterruptedException {

        ShoppingBasket shoppingBasket = ShoppingBasketObjectMother.theHobbiBasket();
        given()
            .contentType(ContentType.JSON)
            .body(shoppingBasket)
            .when()
            .post("/checkout")
            .then()
            .log().ifValidationFails()
            .statusCode(201);
    }

}