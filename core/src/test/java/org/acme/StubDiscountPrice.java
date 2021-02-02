package org.acme;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.test.Mock;

@ApplicationScoped
@Mock
public class StubDiscountPrice implements PriceService {

	@Override
	public double calculate(ShoppingBasket shoppingBasket) {
		return 1000;
	}
    
}