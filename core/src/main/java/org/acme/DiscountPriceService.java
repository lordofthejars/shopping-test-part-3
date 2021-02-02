package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class DiscountPriceService implements PriceService {

	@RestClient
	DiscountGateway discountGateway;

	@Override
	public double calculate(ShoppingBasket shoppingBasket) {
		double total = shoppingBasket.calculateTotal();
		String responseDiscount = discountGateway.getDiscount(Double.toString(total));
		double discount = Double.parseDouble(responseDiscount);

		return total * (1 - discount);
	}
    
}