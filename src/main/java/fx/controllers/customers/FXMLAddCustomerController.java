/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import model.User;
import services.CustomersServices;
import services.UserService;
import utils.Constantes;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class FXMLAddCustomerController implements Initializable {
    private final Alert alert = new Alert(AlertType.ERROR);
    @FXML
    private TextField userName;
    @FXML
    private TextField userPass;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField addressBox;
    @FXML
    private ListView<Customer> customerList;

    public void loadCustomersList() {
        CustomersServices cs = new CustomersServices();
        userName.clear();
        userPass.clear();
        nameBox.clear();
        phoneBox.clear();
        addressBox.clear();
        customerList.getItems().setAll(cs.getAllCustomers());
    }

    public void addCustomer() {
        CustomersServices customersServices = new CustomersServices();
        UserService userService = new UserService();
        if (userName.getText().isEmpty() || userPass.getText().isEmpty()){
            alert.setContentText(Constantes.USERNAME_AND_PASSWORD_PLEASE);
            alert.showAndWait();
        }else if (userService.checkUserByName(userName.getText()) > 0){
            alert.setContentText(Constantes.CHOSE_OTHER);
            alert.showAndWait();
        }else{
            String pass = userPass.getText();
            String username = userName.getText();
            String name = nameBox.getText();
            String phone = phoneBox.getText();
            String address = addressBox.getText();
            User user = new User(username,pass);

            Customer customer = new Customer(name, phone, address);
            if (customersServices.addCustomer(customer,user)) {
                customerList.getItems().add(customer);
            } else {
                alert.setContentText(Constantes.CUSTOMER_NOT_ADDED);
                alert.showAndWait();
            }
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
