package fx.controllers.items;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Item;
import services.ItemsServices;
import utils.Constantes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FXMLUpdateItemController {
    public TextField nameBox;
    public TextField companyBox;
    public TextField priceBox;
    public ListView<Item> lvListItems;
    private final Alert alert = new Alert(Alert.AlertType.ERROR);

    public void showInfo() {
        Item item = lvListItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            nameBox.setText(item.getName());
            companyBox.setText(item.getCompany());
            priceBox.setText(String.valueOf(item.getPrice()));
        }
    }

    public void updateItem() {
        ItemsServices itemsServices = new ItemsServices();
        Item item = lvListItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            double price = Double.parseDouble(priceBox.getText());
            item.setName(nameBox.getText());
            item.setCompany(companyBox.getText());
            //I let the user enter more values but I only take the first two.
            BigDecimal bigDecimal = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
            price = bigDecimal.doubleValue();
            item.setPrice(price);
            if (itemsServices.updateItem(item) > 0) {
                for (int i = 0; i < lvListItems.getItems().size(); i++) {
                    if (lvListItems.getItems().get(i) == item) {
                        lvListItems.getItems().set(i, item);
                    }
                }
            } else {
                alert.setContentText(Constantes.ITEM_NOT_UPDATE);
                alert.showAndWait();
            }


        }
    }

    public void loadItems() {
        ItemsServices itemsServices = new ItemsServices();
        lvListItems.getItems().setAll(itemsServices.getAll());
    }
}
