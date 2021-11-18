/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import model.Customer;
import model.User;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

/**
 *
 */
public interface DAOCustomers {
     
    Customer get(int id);
     
    List<Customer> getAll() ;
     
    boolean saveWithUser(Customer customer, User user);
     
    int update(Customer t);
     
    int deleteWithUser(int id);

    Customer findCustomerByID(int id);
}
