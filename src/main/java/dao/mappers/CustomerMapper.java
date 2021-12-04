package dao.mappers;

import model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setIdCustomer(rs.getInt(1));
        customer.setName(rs.getString(2));
        customer.setTelephone(rs.getString(3));
        customer.setAddress(rs.getString(4));

        return customer;
    }
}
