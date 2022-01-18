/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.util.StringConverter;
import model.Customer;
import model.Item;
import model.Purchase;
import services.CustomersServices;
import services.ItemsServices;
import services.PurchasesServices;
import utils.Constantes;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLAddPurchasesController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private ComboBox<Customer> customerBox;
    @FXML
    private ComboBox<Item> itemBox;
    @FXML
    private ListView<Purchase> purchaseList;
    @FXML
    private DatePicker dateBox;


    public void load() {
        loadItemsList();
        loadCustomersList();
        loadPurchasesList();

    }

    public void loadPurchasesList() {
        PurchasesServices ps = new PurchasesServices();
        List<Purchase> listPurchases = ps.getAllPurchases();
        purchaseList.getItems().clear();
        purchaseList.getItems().addAll(listPurchases);
    }

    public void loadItemsList() {
        ItemsServices itemsServices = new ItemsServices();
        List<Item> li = itemsServices.getAll();
        itemBox.getItems().clear();
        for (Item it : li) {
            itemBox.getItems().add(it);
        }
        itemBox.setConverter(new StringConverter<Item>() {
            @Override
            public String toString(Item item) {
                return item == null ? null : item.getName();
            }

            @Override
            public Item fromString(String s) {
                return null;
            }
        });

    }

    public void loadCustomersList() {
        CustomersServices customersServices = new CustomersServices();
        List<Customer> lc = customersServices.getAllCustomers();
        customerBox.getItems().clear();
        for (Customer cu : lc) {
            customerBox.getItems().add(cu);
        }
        customerBox.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {
                return customer == null ? null : customer.getName();
            }

            @Override
            public Customer fromString(String s) {
                return null;
            }
        });
    }

    public void addPurchase() {
        PurchasesServices ps = new PurchasesServices();
        Customer cu = customerBox.getSelectionModel().getSelectedItem();
        Item it = itemBox.getSelectionModel().getSelectedItem();
        LocalDate date = dateBox.getValue();
        Purchase purchase = new Purchase(cu, it, date);
        if (cu != null && it != null && dateBox.getValue() != null) {
            if (ps.savePurchase(purchase)) {
                purchaseList.getItems().add(purchase);
            } else {
                alert.setContentText(Constantes.PURCHASE_NOT_ADDED);
                alert.showAndWait();
            }


        } else {
            alert.setContentText(Constantes.NOTICE_FIELDS);
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
