package dao.springJDBC;

import dao.DAOUsers;
import dao.DBConPool;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Querys;

import java.util.logging.Logger;

public class SpringDAOUsers implements DAOUsers {
    @Override
    public int checkUser(String name, String password) {
        int confirmacion = -1;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        try {
            confirmacion = jdbcTemplate.queryForObject(Querys.CHECK_USER,Integer.class,name,password);

        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(SpringDAOCustomers.class.getName());
        }
        return confirmacion;
    }

    @Override
    public int checkUserByName(String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.queryForObject(Querys.CHECK_USER_BY_NAME,Integer.class,name);

    }
}
