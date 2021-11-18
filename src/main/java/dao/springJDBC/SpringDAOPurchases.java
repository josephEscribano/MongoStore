package dao.springJDBC;

import dao.DAOPurchases;
import dao.DBConPool;
import dao.springJDBC.mappers.PurchasesMapper;
import model.Purchase;
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpringDAOPurchases implements DAOPurchases {


    @Override
    public int getPurchasesByItemId(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return  jdbcTemplate.queryForObject(Querys.SELECT_PURCHASES_BY_ITEM_QUERY,Integer.class,id);
    }

    @Override
    public int getPurchasesByReviewId(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.queryForObject(Querys.SELECT_PURCHASE_IN_REVIEW_QUERY,Integer.class,id);

    }

    @Override
    public List<Purchase> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.query(Querys.SELECT_PURCHASES_QUERY, new PurchasesMapper());
    }

    @Override
    public List<Purchase> getAllPurchaseForUser(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        return jdbcTemplate.query(Querys.SELECT_PURCHASES_BY_CUSTOMER_QUERY,new PurchasesMapper(),id);
    }


    @Override
    public boolean save(Purchase purchase) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        java.util.Date date = Date.from(purchase.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
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
            Logger.getLogger(SpringDAOPurchases.class.getName()).log(Level.SEVERE,null,e);
        }


        return confirmacion;
    }

    @Override
    public int update(Purchase purchase) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        java.util.Date date = Date.from(purchase.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return jdbcTemplate.update(Querys.UPDATE_PURCHASES_QUERY,
                new java.sql.Date(date.getTime()),purchase.getIdPurchase());

    }

    @Override
    public int searchCustomerByid(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.queryForObject(Querys.SELECT_PURCHASE_COUNT_CUSTOMERS,Integer.class,id);

    }

    @Override
    public int delete(int id) {
        int res = -1;

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
            res = jdbcTemplate.update(Querys.DELETE_PURCHASE_QUERY,id);
        }catch (DataIntegrityViolationException e){
            res = -2;
        }catch (Exception ex){
            Logger.getLogger(SpringDAOPurchases.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }



    @Override
    public List<Purchase> findPurchaseByDate(Date date) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        return jdbcTemplate.query(Querys.SELECT_PURCHASE_BY_DATE_QUERY,new PurchasesMapper(),new java.sql.Date(date.getTime()));

    }
}
