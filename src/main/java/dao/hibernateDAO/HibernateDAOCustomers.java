package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOCustomers;
import model.Customer;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateQuerys;
import utils.Querys;

import java.util.List;

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
        Session session;
        session = HibernateUtils.getSession();
        List<Customer> list = session.createQuery(HibernateQuerys.FROM_CUSTOMER,Customer.class).list();
        session.close();
        return list;
    }

    @Override
    public boolean saveWithUser(Customer customer, User user) {
        return false;
    }

    @Override
    public int update(Customer t) {
        return 0;
    }

    @Override
    public int deleteWithUser(int id) {
        return 0;
    }

    @Override
    public Customer findCustomerByID(int id) {
        return null;
    }
}
