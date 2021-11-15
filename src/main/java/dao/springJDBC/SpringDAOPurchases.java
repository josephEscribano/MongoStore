package dao.springJDBC;

import dao.DAOPurchases;
import dao.DBConPool;
import dao.jdbcDAO.JDBCDAOItems;
import dao.springJDBC.mappers.ItemRowMapper;
import dao.springJDBC.mappers.PurchasesMapper;
import model.Purchase;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import utils.Querys;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class SpringDAOPurchases implements DAOPurchases {
    @Override
    public Purchase get(int id) {

        return null;
    }

    @Override
    public List<Purchase> getPurchasesByItemId(int id) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByReviewId(int id) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.query(Querys.SELECT_PURCHASES_QUERY, new PurchasesMapper());
    }

    @Override
    public boolean save(Purchase purchase) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        java.util.Date date =  java.sql.Date.from(purchase.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        boolean confirmacion = false;
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update( con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement(Querys.INSERT_PURCHASE_QUERY,
                                Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDate(1,new java.sql.Date(date.getTime()));
                preparedStatement.setInt(2, purchase.getCustomer().getIdCustomer());
                preparedStatement.setInt(3, purchase.getItem().getIdItem());
                return preparedStatement;
            },keyHolder);

            purchase.setIdPurchase(keyHolder.getKey().intValue());


            confirmacion = true;
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(JDBCDAOItems.class.getName());
        }


        return confirmacion;
    }

    @Override
    public boolean update(Purchase t) {
        return false;
    }

    @Override
    public List<Purchase> searchCustomerByid(int id) {
        return null;
    }

    @Override
    public boolean delete(Purchase t) {
        return false;
    }

    @Override
    public List<Purchase> findPurchaseByDate(Date date) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        return jdbcTemplate.query(Querys.SELECT_PURCHASE_BY_DATE_QUERY,new PurchasesMapper(),new java.sql.Date(date.getTime()));

    }
}
