package dao.springJDBC;

import dao.DAOCustomers;
import dao.DBConPool;
import dao.springJDBC.mappers.CustomerMapper;
import model.Customer;
import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import utils.Querys;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
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
    public boolean saveWithUser(Customer customer, User user) {
        boolean confirmacion = false;
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConPool.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);

        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(transactionManager.getDataSource());
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update( con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement(Querys.INSERT_USER_QUERY,
                                Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2, user.getPassword());
                return preparedStatement;
            },keyHolder);
            user.setId(keyHolder.getKey().intValue());

            jdbcTemplate.update( con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement(Querys.INSERT_CUSTOMER_QUERY,
                                Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1,user.getId());
                preparedStatement.setString(2,customer.getName());
                preparedStatement.setString(3, customer.getPhone());
                preparedStatement.setString(4, customer.getAddress());

                return preparedStatement;
            },keyHolder);

            customer.setIdCustomer(user.getId());

            confirmacion = true;
            transactionManager.commit(txStatus);
        }catch (Exception e){
            transactionManager.rollback(txStatus);
            Logger.getLogger(SpringDAOCustomers.class.getName()).log(Level.SEVERE,null,e);
        }


        return confirmacion;
    }

    @Override
    public int update(Customer customer) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.update(Querys.UPDATE_CUSTOMER_QUERY,
                customer.getName(),customer.getPhone(),customer.getAddress(),customer.getIdCustomer());


    }

    @Override
    public int deleteWithUser(int id) {
        int res ;
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConPool.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);


        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(transactionManager.getDataSource());

            jdbcTemplate.update(Querys.DELETE_CUSTOMER_QUERY,id);
            res = jdbcTemplate.update(Querys.DELETE_USER,id);
            transactionManager.commit(txStatus);
        }catch (Exception e){
            res = -2;
            transactionManager.rollback(txStatus);

            Logger.getLogger(SpringDAOCustomers.class.getName()).log(Level.SEVERE,null,e);

        }
        return res;
    }

    @Override
    public Customer findCustomerByID(int id) {
        JdbcTemplate jtm = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        Customer customer = null;
        try {
            customer = jtm.queryForObject(Querys.SELECT_CUSTOMER_BY_ID_QUERY,new CustomerMapper(),id);
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(SpringDAOCustomers.class.getName());
        }
        return customer;
    }
}
