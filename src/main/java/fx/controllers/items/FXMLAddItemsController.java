package fx.controllers.items;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Item;
import services.ItemsServices;
import utils.Constantes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAddItemsController implements Initializable {

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ListView<Item> listItems;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField companyBox;
    @FXML
    private TextField priceBox;

    public void saveItem() {

        ItemsServices it = new ItemsServices();
        String name = nameBox.getText();
        String company = companyBox.getText();
        double price = Double.parseDouble(priceBox.getText());
        //I let the user enter more values but I only take the first two.
        BigDecimal bigDecimal = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
        price = bigDecimal.doubleValue();
        Item item = new Item(name, company, price);
        if (it.save(item)) {
            listItems.getItems().add(item);
        } else {
            alert.setContentText(Constantes.ITEM_NOT_ADDED);
            alert.showAndWait();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadItems() {
        ItemsServices is = new ItemsServices();
        nameBox.clear();
        companyBox.clear();
        priceBox.clear();
        listItems.getItems().setAll(is.getAll());
    }
}
