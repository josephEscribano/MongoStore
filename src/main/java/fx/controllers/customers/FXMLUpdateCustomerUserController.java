package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import services.CustomersServices;
import utils.Constantes;

public class FXMLUpdateCustomerUserController {


    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private final Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
    public TextField nameBox;
    public TextField phoneBox;
    public TextField addressBox;
    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void showInfo() {
        CustomersServices customersServices = new CustomersServices();
        Customer customer = customersServices.searchById(principal.getIdUser());
        nameBox.setText(customer.getName());
        phoneBox.setText(customer.getPhone());
        addressBox.setText(customer.getAddress());
    }

    public void updateCustomer() {
        CustomersServices customersServices = new CustomersServices();
        Customer customer = customersServices.searchById(principal.getIdUser());
        if (customer != null) {
            customer.setName(nameBox.getText());
            customer.setPhone(phoneBox.getText());
            customer.setAddress(addressBox.getText());
            if (customersServices.updateCustomers(customer) > 0) {
                alert2.setContentText(Constantes.CUSTOMER_HAS_BEEN_UPDATE);
                alert2.showAndWait();
            } else {
                alert.setContentText(Constantes.CUSTOMER_NOT_UPDATE);
                alert.showAndWait();
            }


        }

    }

}
