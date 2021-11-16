package fx.controllers.purchases;

import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import model.Purchase;
import services.PurchasesServices;
import utils.Constantes;

public class FXMLUpdatePurchasesController {
    public DatePicker dateBox;
    public ListView<Purchase> purchaseList;
    private final Alert alert = new Alert(Alert.AlertType.ERROR);

    public void showInfo() {
        Purchase purchase = purchaseList.getSelectionModel().getSelectedItem();
        if (purchase != null) {
            dateBox.setValue(purchase.getDate());
        }
    }

    public void updatePurchases() {
        PurchasesServices purchasesServices = new PurchasesServices();
        Purchase purchase = purchaseList.getSelectionModel().getSelectedItem();
        if (purchase != null) {
            purchase.setDate(dateBox.getValue());
            if (purchasesServices.updatePurchases(purchase)) {
                for (int i = 0; i < purchaseList.getItems().size(); i++) {
                    if (purchaseList.getItems().get(i) == purchase) {
                        purchaseList.getItems().set(i, purchase);
                    }
                }
            } else {
                alert.setContentText(Constantes.PURCHASE_NOT_UPDATE);
                alert.showAndWait();
            }


        }
    }

    public void loadItems() {
        PurchasesServices purchasesServices = new PurchasesServices();
        purchaseList.getItems().setAll(purchasesServices.getAllPurchases());
    }
}
