package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOPurchases;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.dao.DataIntegrityViolationException;
import utils.HibernateQuerys;

import java.util.Date;
import java.util.List;

@Log4j2
public class HibernateDAOPurchases implements DAOPurchases {

    @Override
    public int getPurchasesByItemId(int id) {
        Session session = null;
        int confirmacion = 0;
        try {
            session = HibernateUtils.getSession();
            String query = "select count(p.idPurchase) from Purchase p where p.itemsByIdItem.id = :id";
            confirmacion = (session.createQuery(query,Long.class).setParameter("id",id).uniqueResult()).intValue();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }

    @Override
    public int getPurchasesByReviewId(int id) {
        Session session = null;
        int confirmacion = 0;
        try{
            session = HibernateUtils.getSession();
            String query = "select count (*) from Purchase p join Review r on p.idPurchase = r.purchasesByIdPurchase.idPurchase where r.purchasesByIdPurchase.idPurchase = :id";
            confirmacion = (session.createQuery(query,Long.class).setParameter("id",id).uniqueResult()).intValue();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
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
        Session session = null;
        List<Purchase> list = null;
        try {
            session = HibernateUtils.getSession();
            list = session.createQuery("from Purchase where customersByIdCustomer.id = :id",Purchase.class).setParameter("id",id).list();
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
    public boolean save(Purchase purchase) {
        Session session = null;
        boolean confirmacion = false;
        try{
            session = HibernateUtils.getSession();
            session.save(purchase);
            confirmacion = true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }

    @Override
    public int update(Purchase purchase) {
        Session session = null;
        Transaction transaction = null;
        int confirmacion = 1;
        try{
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.update(purchase);

            transaction.commit();
            confirmacion = 0;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }

    @Override
    public int searchCustomerByid(int id) {
        int confirmacion = 0;
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            String query = "select count(p.customersByIdCustomer.id) from Purchase p where p.customersByIdCustomer.id = :id";
            int numero = (session.createQuery(query,Long.class).setParameter("id",id).uniqueResult()).intValue();
            transaction.commit();
            confirmacion = numero;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }

    @Override
    public int delete(Purchase purchase) {
        Session session = null;
        Transaction transaction = null;
        int confirmacion ;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(purchase);
            transaction.commit();
            confirmacion = 1;
        }catch (DataIntegrityViolationException e){
            log.error(e.getMessage(),e);
            if (transaction != null){
                transaction.rollback();
            }
            confirmacion = -2;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            if (transaction != null){
                transaction.rollback();
            }
            confirmacion = -1;
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }

    @Override
    public List<Purchase> findPurchaseByDate(Date date) {
        return null;
    }
}
