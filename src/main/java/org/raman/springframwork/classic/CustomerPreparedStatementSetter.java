package org.raman.springframwork.classic;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.raman.springframwork.classic.domain.Customer;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.stereotype.Component;
@Component
public class CustomerPreparedStatementSetter implements ItemPreparedStatementSetter<Customer> {

	@Override
	public void setValues(Customer customer, PreparedStatement ps) throws SQLException {
		ps.setLong(1, customer.getId());
		ps.setString(2, customer.getFirstName());
		ps.setString(3, customer.getLastName());
		ps.setString(4, customer.getCardNumber());		
	}

}
