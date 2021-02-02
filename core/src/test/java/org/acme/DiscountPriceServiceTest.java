package org.acme;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class DiscountPriceServiceTest {

    private DiscountPriceService discountPriceService = new DiscountPriceService();

    @Test
    public void should_not_discount_if_no_limits() {
        DiscountGateway discountGateway = mock(DiscountGateway.class);
        when(discountGateway.getDiscount(anyString())).thenReturn("0");
        discountPriceService.discountGateway = discountGateway;

        ShoppingBasket shoppingBasket = ShoppingBasketObjectMother.theHobbiBasket();
        double total = discountPriceService.calculate(shoppingBasket);

        assertThat(total).isEqualTo(15);

    }

}