/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

import dao.DAOFactory;
import model.Customer;
import model.User;

public class CustomersServices {

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

    public int deleteCustomer(int id) {
        int confirmacion = 1;
        int tienePurchase = dao.getDAOPurchases().searchCustomerByid(id);

        if (tienePurchase == 0){
            int resultado = dao.getDAOCustomers().deleteWithUser(id);
            if (resultado < 0){
                confirmacion = resultado;
            }else{
                confirmacion = 0;
            }

        }

        return confirmacion;
    }

    public boolean addCustomer(Customer customer, User user)  {

        return dao.getDAOCustomers().saveWithUser(customer,user);

    }

}
