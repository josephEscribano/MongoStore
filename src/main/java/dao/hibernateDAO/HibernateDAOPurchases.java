package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOPurchases;
import model.Customer;
import model.Purchase;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateQuerys;

import java.util.Date;
import java.util.List;

public class HibernateDAOPurchases implements DAOPurchases {

    @Override
    public int getPurchasesByItemId(int id) {
        return 0;
    }

    @Override
    public int getPurchasesByReviewId(int id) {
        return 0;
    }

    @Override
    public List<Purchase> getAll() {
        Session session;
        session = HibernateUtils.getSession();
        List<Purchase> list = session.createQuery(HibernateQuerys.FROM_PURCHASE_,Purchase.class).list();
        session.close();
        return list;
    }

    @Override
    public List<Purchase> getAllPurchaseForUser(int id) {
        return null;
    }

    @Override
    public boolean save(Purchase t) {
        return false;
    }

    @Override
    public int update(Purchase t) {
        return 0;
    }

    @Override
    public int searchCustomerByid(int id) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Purchase> findPurchaseByDate(Date date) {
        return null;
    }
}
