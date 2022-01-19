package fx.controllers.customers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import model.Purchase;
import services.CustomersServices;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLListCustomerController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddress;
    @FXML
    private ListView<Purchase> lvPurchases;
    @FXML
    private ListView<Customer> lvCustomer;

    public void load() {
        CustomersServices customersServices = new CustomersServices();
        lvCustomer.getItems().setAll(customersServices.getAllCustomers());
    }

    public void showData(){
        CustomersServices customersServices = new CustomersServices();
        Customer customer = lvCustomer.getSelectionModel().getSelectedItem();

        tfName.setText(customer.getName());
        tfPhone.setText(customer.getTelephone());
        tfAddress.setText(customer.getAddress());
        List<Purchase> listPurchases = customersServices.getPurchasesByCustomer(customer.getIdCustomer());

        lvPurchases.getItems().setAll(listPurchases);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
