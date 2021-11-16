/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import model.Purchase;
import services.PurchasesServices;
import utils.Constantes;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLDeleteController implements Initializable {
    private final Alert alert = new Alert(AlertType.INFORMATION);
    @FXML
    private ListView<Purchase> purchaseBox;


    public void loadPurchases() {
        PurchasesServices ps = new PurchasesServices();
        purchaseBox.getItems().clear();
        purchaseBox.getItems().addAll(ps.getAllPurchases());
    }

    public void deletePurchase() {

        Purchase p = purchaseBox.getSelectionModel().getSelectedItem();
        PurchasesServices purchasesServices = new PurchasesServices();

        if (p != null) {
            int resultado = purchasesServices.deletePurchase(p.getIdPurchase());
            if (resultado > 0) {
                purchaseBox.getItems().remove(p);
            } else if (resultado == -2){
                alert.setContentText(Constantes.PURCHASE_NOT_DELETED);
                alert.showAndWait();
            }else if (resultado == -1){
                alert.setContentText(Constantes.EXIST_REVIEW_ASSOCIATED);
                alert.showAndWait();
            } else if (resultado == 0){
                alert.setContentText(Constantes.PURCHASE_NOT_FOUND);
                alert.showAndWait();
            }

//            if (purchasesServices.getPurchasesByReviewId(p.getIdPurchase()).isEmpty()) {
//                if (purchasesServices.deletePurchase(p.getIdPurchase()) > 0) {
//                    purchaseBox.getItems().remove(p);
//                } else {
//                    alert.setContentText(Constantes.PURCHASE_NOT_DELETED);
//                    alert.showAndWait();
//                }
//            } else {
//                alert.setContentText(Constantes.EXIST_REVIEW_ASSOCIATED);
//                alert.showAndWait();
//            }


        } else {
            alert.setContentText(Constantes.SELECT_PURCHASE);
            alert.showAndWait();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
