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

import java.time.LocalDate;
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
            confirmacion = (session.createNamedQuery("purchaseByItem",Long.class).setParameter("id",id).uniqueResult()).intValue();
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
            confirmacion = (session.createNamedQuery("purchaseByReview",Long.class).setParameter("id",id).uniqueResult()).intValue();
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
            list = session.createNamedQuery("getPurchase",Purchase.class).getResultList();
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
            list = session.createNamedQuery("getPurchasesByUser",Purchase.class).setParameter("id",id).getResultList();
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
            int numero = (session.createNamedQuery("countPurhcaseByCustomer",Long.class).setParameter("id",id).uniqueResult()).intValue();
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
    public List<Purchase> findPurchaseByDate(LocalDate localDate) {
        Session session = null;
        List<Purchase> list = null;
        try {
            session = HibernateUtils.getSession();
            list = session.createNamedQuery("purchaseByDate",Purchase.class)
                    .setParameter("date",localDate)
                    .getResultList();
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
    public List<Purchase> purchasesBetweenDates(LocalDate initialDate, LocalDate finalDate) {
        Session session = null;
        List<Purchase> list = null;
        try {
            session = HibernateUtils.getSession();
            list = session.createNamedQuery("purchaseBetweenDates",Purchase.class)
                    .setParameter("initialDate",initialDate)
                    .setParameter("finalDate",finalDate)
                    .getResultList();
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
    public List<Purchase> getPurchasesByItem(int id) {
        Session session = null;
        List<Purchase> list = null;
        try {
            session = HibernateUtils.getSession();
            list = session.createNamedQuery("getPurchasesByItem",Purchase.class)
                    .setParameter("id",id)
                    .getResultList();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }


}
