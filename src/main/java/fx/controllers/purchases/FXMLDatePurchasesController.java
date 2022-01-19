/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import model.Purchase;
import services.PurchasesServices;
import utils.Constantes;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLDatePurchasesController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private DatePicker dateBox;
    @FXML
    private ListView<Purchase> purchaseList;


    public void searchByDate() {
        PurchasesServices purchasesServices = new PurchasesServices();
        java.util.Date date = Date.from(dateBox.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        List<Purchase> listPurchases = purchasesServices.findPurchaseByDate(dateBox.getValue());
        if (!listPurchases.isEmpty()) {
            purchaseList.getItems().setAll(listPurchases);
        } else {
            alert.setContentText(Constantes.PURCHASE_NOT_EXIST);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


}
