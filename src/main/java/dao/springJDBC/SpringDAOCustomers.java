package dao.springJDBC;

import dao.DAOCustomers;
import dao.DBConPool;
import dao.jdbcDAO.JDBCDAOItems;
import dao.springJDBC.mappers.CustomerMapper;
import model.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import utils.Querys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

public class SpringDAOCustomers implements DAOCustomers {

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll()  {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        return jdbcTemplate.query(Querys.SELECT_CUSTOMERS_QUERY, new CustomerMapper());
    }

    @Override
    public boolean save(Customer customer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        boolean confirmacion = false;
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update( con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement(Querys.INSERT_CUSTOMER_QUERY,
                                Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,customer.getName());
                preparedStatement.setString(2, customer.getPhone());
                preparedStatement.setString(3, customer.getAddress());
                return preparedStatement;
            },keyHolder);

            customer.setIdCustomer(keyHolder.getKey().intValue());


            confirmacion = true;
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(JDBCDAOItems.class.getName());
        }


        return confirmacion;
    }

    @Override
    public boolean update(Customer customer) {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
            jdbcTemplate.update(Querys.UPDATE_CUSTOMER_QUERY,
                    customer.getName(),customer.getPhone(),customer.getAddress(),customer.getIdCustomer());
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(JDBCDAOItems.class.getName());
        }

        return false;
    }

    @Override
    public boolean delete(Customer t) {
        return false;
    }

    @Override
    public Customer findCustomerByID(int id) {
        JdbcTemplate jtm = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        Customer customer = null;
        try {
            customer = jtm.queryForObject(Querys.SELECT_CUSTOMER_BY_ID_QUERY,new CustomerMapper(),id);
        }catch (EmptyResultDataAccessException e){

            Logger.getLogger(JDBCDAOItems.class.getName());
        }
        return customer;
    }
}
