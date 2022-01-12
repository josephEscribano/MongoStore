package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOCustomers;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateQuerys;
import utils.Querys;

import java.io.Serializable;
import java.util.List;

@Log4j2
public class HibernateDAOCustomers implements DAOCustomers {

    @Override
    public Customer get(int id) {
        Session session = HibernateUtils.getSession();
        Customer customer = session.get(Customer.class,id);

        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = null;
        List<Customer> list = null;
        try{
            session = HibernateUtils.getSession();
            list = session.createQuery(HibernateQuerys.FROM_CUSTOMER,Customer.class).list();
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
    public boolean saveWithUser(Customer customer, User user) {
        boolean confirmacion = false;
        Session session = null;
        Transaction transaction = null;
        try{

            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            customer.setIdCustomer((int) session.save(user));
            session.save(customer);
            transaction.commit();
            confirmacion = true;
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
    public int update(Customer customer) {
        int confirmacion = 0;
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            confirmacion = 1;
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
    public int deleteWithUser(Customer customer) {
        int confirmacion ;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(customer);
            User user = session.get(User.class, customer.getIdCustomer());
            session.delete(user);
            transaction.commit();
            confirmacion = 1;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            if (transaction != null){
                transaction.rollback();
            }
            confirmacion = -2;
        }finally {
            if (session != null){
                session.close();
            }
        }

        return confirmacion;
    }

    @Override
    public Customer findCustomerByID(int id) {
        return null;
    }
}
