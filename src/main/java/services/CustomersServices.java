/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

import dao.DAOFactory;
import javafx.scene.control.Alert;
import model.Customer;

/**
 *
 * @author Laura
 */
public class CustomersServices {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private DAOFactory dao;

    public CustomersServices(){
        dao = new DAOFactory();
    }

    public int updateCustomers(Customer customer){
        return dao.getDAOCustomers().update(customer);
    }
    public List<Customer> getAllCustomers()  {
        return dao.getDAOCustomers().getAll();
    }

    public Customer searchById(int id)  {
        return dao.getDAOCustomers().findCustomerByID(id);
    }

    public boolean deleteCustomer(Customer customer) {
        return dao.getDAOCustomers().delete(customer);
    }

    public boolean addCustomer(Customer customer)  {

        return dao.getDAOCustomers().save(customer);

    }

}
