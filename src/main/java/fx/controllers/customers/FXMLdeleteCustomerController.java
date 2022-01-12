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
import model.Customer;
import services.CustomersServices;
import services.PurchasesServices;
import utils.Constantes;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLdeleteCustomerController implements Initializable {
    private final Alert alert = new Alert(AlertType.INFORMATION);

    @FXML
    private ListView<Customer> customerBox;

    public void loadCustomersList() {
        CustomersServices cs = new CustomersServices();
        customerBox.getItems().setAll(cs.getAllCustomers());
    }

    public void deleteCustomer() {
        CustomersServices cs = new CustomersServices();
        Customer customer = customerBox.getSelectionModel().getSelectedItem();
        if (customer != null) {
            int confirmacion = cs.deleteCustomer(customer);
            if (confirmacion == 0) {
                customerBox.getItems().remove(customer);
            }else if (confirmacion == 1){
                alert.setContentText(Constantes.EXIST_PURCHASE_ASSOCIATED);
                alert.showAndWait();
            }else if (confirmacion < 0){
                alert.setContentText(Constantes.CUSTOMER_NOT_DELETED);
                alert.showAndWait();
            }
        } else {
            alert.setContentText(Constantes.SELECT_CUSTOMER);
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
