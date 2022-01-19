package fx.controllers.purchases;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Item;
import model.Purchase;
import services.CustomersServices;
import services.ItemsServices;
import services.PurchasesServices;
import utils.Constantes;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListPurchasesController {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private Label labelInitialDate;
    @FXML
    private Label labelFinalDate;
    @FXML
    private Button btSearch;
    @FXML
    private ComboBox<String> cbTypes;
    @FXML
    private ListView<Purchase> purchasesList;
    @FXML
    private DatePicker initialDate;
    @FXML
    private DatePicker finalDate;
    @FXML
    private ListView<Customer> lvCustomer;
    @FXML
    private ListView<Item> lvItems;

    public void load(){
        PurchasesServices purchasesServices = new PurchasesServices();
        cbTypes.getItems().setAll("Customer","Item","Date");
        purchasesList.getItems().setAll(purchasesServices.getAllPurchases());
    }

    public void showData(){
        PurchasesServices purchasesServices = new PurchasesServices();
        CustomersServices customersServices = new CustomersServices();
        ItemsServices itemsServices = new ItemsServices();
        String selected = cbTypes.getSelectionModel().getSelectedItem();
        if (selected != null){
            if (selected.equals("Customer")){
                lvCustomer.getItems().setAll(customersServices.getAllCustomers());
                lvItems.setVisible(false);
                initialDate.setVisible(false);
                finalDate.setVisible(false);
                btSearch.setVisible(false);
                labelInitialDate.setVisible(false);
                labelFinalDate.setVisible(false);
                lvCustomer.setVisible(true);

            }else if (selected.equals("Item")){
                lvItems.getItems().setAll(itemsServices.getAll());
                lvCustomer.setVisible(false);
                initialDate.setVisible(false);
                finalDate.setVisible(false);
                btSearch.setVisible(false);
                labelInitialDate.setVisible(false);
                labelFinalDate.setVisible(false);
                lvItems.setVisible(true);

            }else{
                lvCustomer.setVisible(false);
                lvItems.setVisible(false);
                labelInitialDate.setVisible(true);
                initialDate.setVisible(true);
                labelFinalDate.setVisible(true);
                finalDate.setVisible(true);
                btSearch.setVisible(true);
            }
        }
    }



    public void loadPurchasesByCustomer() {
        PurchasesServices purchasesServices = new PurchasesServices();
        Customer customer = lvCustomer.getSelectionModel().getSelectedItem();
        if (customer !=null){
            purchasesList.getItems().setAll(purchasesServices.getPurchasesForUser(customer.getIdCustomer()));
        }
    }


    public void loadPurchasesByItem(){
        PurchasesServices purchasesServices = new PurchasesServices();
        Item item = lvItems.getSelectionModel().getSelectedItem();
        if (item != null){
            purchasesList.getItems().setAll(purchasesServices.getPurchasesByItem(item.getIdItem()));
        }
    }

    public void searchBetweenDates() {
        PurchasesServices purchasesServices = new PurchasesServices();
        String selected = cbTypes.getSelectionModel().getSelectedItem();

        if (selected != null){
            if (selected.equals("Date")){
                if (initialDate.getValue() != null && finalDate.getValue() != null){
                    purchasesList.getItems().setAll(purchasesServices.purchasesBetweenDates(initialDate.getValue(),finalDate.getValue()));
                }else{
                    alert.setContentText(Constantes.SELECT_DATE);
                    alert.showAndWait();
                }
            }
        }
    }
}
