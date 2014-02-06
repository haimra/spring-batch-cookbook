package org.raman.springframwork.classic;

import org.raman.springframwork.classic.domain.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
@Component
public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

	@Override
	public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
		Customer c = new Customer();
		c.setId(fieldSet.readLong("id"));
		c.setFirstName(fieldSet.readString("firstName"));
		c.setLastName(fieldSet.readString("lastName"));
		c.setCardNumber(fieldSet.readString("cardNumber"));
		return c;
	}

}
