package dao.springJDBC;

import dao.DAOPurchases;
import dao.DBConPool;
import dao.springJDBC.mappers.ItemRowMapper;
import dao.springJDBC.mappers.PurchasesMapper;
import model.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Querys;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
    public boolean save(Purchase t) {
        return false;
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

        return jdbcTemplate.query(Querys.SELECT_PURCHASE_BY_DATE_QUERY, new Object[]{new java.sql.Date(date.getTime())},new PurchasesMapper());

    }
}
