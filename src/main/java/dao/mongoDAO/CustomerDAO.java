package dao.mongoDAO;

import dao.interfaces.DAOCustomers;
import model.Customer;
import model.User;

import java.util.List;

public class CustomerDAO implements DAOCustomers {
    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
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
    public int deleteWithUser(Customer customer) {
        return 0;
    }

    @Override
    public Customer findCustomerByID(int id) {
        return null;
    }
}
