package dao.springJDBC;

import dao.DAOItems;
import dao.DBConPool;
import dao.springJDBC.mappers.ItemRowMapper;
import lombok.extern.log4j.Log4j2;
import model.Item;
import org.springframework.dao.DataIntegrityViolationException;
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

public class SpringDAOItems implements DAOItems {


    @Override
    public List<Item> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.query(Querys.SELECT_ITEMS_QUERY, new ItemRowMapper());

    }

    @Override
    public boolean save(Item item) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        boolean confirmacion = false;
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update( con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement(Querys.INSERT_ITEM_QUERY,
                                Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,item.getName());
                preparedStatement.setString(2, item.getCompany());
                preparedStatement.setDouble(3, item.getPrice());
                return preparedStatement;
            },keyHolder);

            item.setIdItem(keyHolder.getKey().intValue());


            confirmacion = true;
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(SpringDAOItems.class.getName());
        }


        return confirmacion;
    }

    @Override
    public int update(Item item) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.update(Querys.UPDATE_ITEM_QUERY,
                item.getName(),item.getCompany(),item.getPrice(),item.getIdItem());

    }

    @Override
    public int deletePurchasesAndItem(int id) {
        int res;
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConPool.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(transactionManager.getDataSource());
            jdbcTemplate.update(Querys.DELETE_PURCHASES_BY_IDITEM_QUERY,id);
            res =jdbcTemplate.update(Querys.DELETE_ITEM_QUERY,id);
            transactionManager.commit(txStatus);
        }catch (Exception e){
            transactionManager.rollback(txStatus);
            Logger.getLogger(SpringDAOItems.class.getName()).log(Level.SEVERE, null, e);
            res = -2;
        }
        return res;
    }

    @Override
    public int deleteItem(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        int res ;
        try{
            res = jdbcTemplate.update(Querys.DELETE_ITEM_QUERY,id);
        }catch (DataIntegrityViolationException e){
            res = -2;
        }catch (Exception ex){
            res = -2;
            Logger.getLogger(SpringDAOItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public Item findItemByID(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        Item item = null;
        try{
            item = jdbcTemplate.queryForObject(Querys.SELECT_ITEM_BY_ID_QUERY, new ItemRowMapper(),id);
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(SpringDAOItems.class.getName());
        }
        return item;
    }

    @Override
    public int checkItemReview(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.queryForObject(Querys.COUNT_REVIEW_BY_ITEM,Integer.class,id);

    }

    @Override
    public void closePool() {
        DBConPool.getInstance().closePool();
    }




}
