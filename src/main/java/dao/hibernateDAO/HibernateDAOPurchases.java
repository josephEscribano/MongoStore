package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOPurchases;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Purchase;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateQuerys;

import java.util.Date;
import java.util.List;

@Log4j2
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
        Session session = null;
        List<Purchase> list = null;
        try{
            session = HibernateUtils.getSession();
            list = session.createQuery(HibernateQuerys.FROM_PURCHASE_,Purchase.class).list();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }

        }
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
