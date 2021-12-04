package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import services.CustomersServices;
import utils.Constantes;

public class FXMLUpdateCustomerController {
    public TextField nameBox;

    public ListView<Customer> customerListUpdate;
    public TextField phoneBox;
    public TextField addressBox;

    private final Alert alert = new Alert(Alert.AlertType.ERROR);


    public void showInfo() {
        Customer customer = customerListUpdate.getSelectionModel().getSelectedItem();
        if (customer != null) {
            nameBox.setText(customer.getName());
            phoneBox.setText(customer.getPhone());
            addressBox.setText(customer.getAddress());
        }
    }

    public void updateCustomer() {
        CustomersServices customersServices = new CustomersServices();
        Customer customer = customerListUpdate.getSelectionModel().getSelectedItem();
        if (customer != null) {
            customer.setName(nameBox.getText());
            customer.setPhone(phoneBox.getText());
            customer.setAddress(addressBox.getText());
            if (customersServices.updateCustomers(customer) > 0) {
                for (int i = 0; i < customerListUpdate.getItems().size(); i++) {
                    if (customerListUpdate.getItems().get(i) == customer) {
                        customerListUpdate.getItems().set(i, customer);
                    }
                }
            } else {
                alert.setContentText(Constantes.CUSTOMER_NOT_UPDATE);
                alert.showAndWait();
            }


        }

    }

    public void loadItems() {
        CustomersServices customersServices = new CustomersServices();
        nameBox.clear();
        phoneBox.clear();
        addressBox.clear();
        customerListUpdate.getItems().setAll(customersServices.getAllCustomers());


    }
}
