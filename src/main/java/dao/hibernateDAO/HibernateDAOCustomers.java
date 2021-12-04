package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOCustomers;
import model.Customer;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateQuerys;
import utils.Querys;

import java.util.List;

public class HibernateDAOCustomers implements DAOCustomers {

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        Session session;
        List<Customer> list;
        session = HibernateUtils.getSession();
        Query query = session.createQuery(HibernateQuerys.FROM_CUSTOMER);
        list = query.list();
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
