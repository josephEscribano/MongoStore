package dao.springJDBC.mappers;

import model.Customer;
import model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();

        user.setName(rs.getString(1));
        user.setPassword(rs.getString(2));

        return user;
    }
}
