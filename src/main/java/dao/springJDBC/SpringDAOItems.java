package dao.springJDBC;

import dao.DAOItems;
import dao.DBConPool;
import dao.springJDBC.mappers.ItemRowMapper;
import model.Item;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Querys;

import java.util.List;

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
    public boolean save(Item t) {
        return false;
    }

    @Override
    public boolean update(Item t) {
        return false;
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
        //si no devuelve nada da un error
        Item item = null;
        try{
            item = jdbcTemplate.queryForObject(Querys.SELECT_ITEM_BY_ID_QUERY, new ItemRowMapper(),id);
        }catch (EmptyResultDataAccessException e){
            item = null;
        }
        return item;
    }

    @Override
    public void closePool() {

    }


}
