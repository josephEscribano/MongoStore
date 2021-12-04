package dao.mappers;

import model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();

        item.setIdItem(rs.getInt(1));
        item.setName(rs.getString(2));
        item.setCompany(rs.getString(3));
        item.setPrice(rs.getDouble(4));
        return item;
    }
}
