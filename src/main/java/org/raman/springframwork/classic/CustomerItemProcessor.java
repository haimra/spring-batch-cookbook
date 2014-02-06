package org.raman.springframwork.classic;

import org.raman.springframwork.classic.domain.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer customer) throws Exception {
		String cardNumber = customer.getCardNumber();
		String shaCardNumber = sha256(cardNumber);
		customer.setCardNumber(shaCardNumber);
		return customer;
	}

	private String sha256(String cardNumber) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cardNumber.length(); i++) {
			sb.append(cardNumber.charAt(i)>>2);
		}
		return sb.toString();
	}

}
