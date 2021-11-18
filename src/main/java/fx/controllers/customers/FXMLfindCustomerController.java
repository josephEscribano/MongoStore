/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import services.CustomersServices;
import utils.Constantes;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLfindCustomerController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private TextField dniBox;
    @FXML
    private ListView<Customer> customerList;

    public void dataClear(){
        dniBox.clear();
    }
    public void searchById() {
        CustomersServices cs = new CustomersServices();
        Customer customer = cs.searchById(Integer.parseInt(dniBox.getText()));
        if (customer != null) {
            customerList.getItems().clear();
            customerList.getItems().add(customer);
        } else {
            alert.setContentText(Constantes.CUSTOMER_NOT_EXIST);
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
