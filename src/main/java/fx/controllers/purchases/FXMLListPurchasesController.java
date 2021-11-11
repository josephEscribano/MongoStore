package fx.controllers.purchases;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Purchase;
import services.PurchasesServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListPurchasesController implements Initializable {
    public ListView<Purchase> purchasesList;

    public void load(){
        PurchasesServices purchasesServices = new PurchasesServices();
        purchasesList.getItems().setAll(purchasesServices.getAllPurchases());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
