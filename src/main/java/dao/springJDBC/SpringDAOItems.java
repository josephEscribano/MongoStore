package dao.springJDBC;

import dao.DAOItems;
import dao.DBConPool;
import dao.jdbcDAO.JDBCDAOItems;
import dao.springJDBC.mappers.ItemRowMapper;
import lombok.extern.log4j.Log4j2;
import model.Item;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import utils.Querys;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpringDAOItems implements DAOItems {
    @Override
    public Item get(int id) {


        return null;
    }

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
            Logger.getLogger(JDBCDAOItems.class.getName());
        }


        return confirmacion;
    }

    @Override
    public boolean update(Item item) {

        boolean confirmacion = false;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
            jdbcTemplate.update(Querys.UPDATE_ITEM_QUERY,
                    item.getName(),item.getCompany(),item.getPrice(),item.getIdItem());
            confirmacion = true;
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(JDBCDAOItems.class.getName()).log(Level.SEVERE,null,e);
        }

        return confirmacion;

    }

    @Override
    public boolean deletePurchasesAndItem(Item t) {
        return false;
    }

    @Override
    public boolean deleteItem(Item item) {
        return false;
    }

    @Override
    public Item findItemByID(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        Item item = null;
        try{
            item = jdbcTemplate.queryForObject(Querys.SELECT_ITEM_BY_ID_QUERY, new ItemRowMapper(),id);
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(JDBCDAOItems.class.getName());
        }
        return item;
    }

    @Override
    public void closePool() {

    }


}
