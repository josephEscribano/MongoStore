package dao.mappers;

import model.Customer;
import model.Item;
import model.Purchase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchasesMapper implements RowMapper<Purchase> {
    @Override
    public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
        Purchase purchase;
        purchase = new Purchase(rs.getInt(1)
                ,new Customer(rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6))
                ,new Item(rs.getInt(7),rs.getString(8),rs.getString(9),rs.getDouble(10))
                ,new Date(rs.getDate(2).getTime()).toLocalDate());
        return purchase;
    }
}
