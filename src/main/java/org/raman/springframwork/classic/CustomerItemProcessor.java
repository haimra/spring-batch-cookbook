package org.raman.springframwork.classic;

import org.raman.springframwork.classic.domain.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer customer) throws Exception {
		String cardNumber = customer.getCardNumber();
		if(cardNumber.matches("(\\d{4}-){3}\\d{4}")){
			String shaCardNumber = sha256(cardNumber);
			customer.setCardNumber(shaCardNumber);
			return customer;
		}else{
			throw new Exception("Ilegal card number");
		}
		
	}

	private String sha256(String cardNumber) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cardNumber.length(); i++) {
			sb.append(cardNumber.charAt(i)>>2);
		}
		return sb.toString();
	}

}
