package fx.controllers.items;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Item;
import services.ItemsServices;
import utils.Constantes;

public class FXMLFindItemController {
    public ListView<Item> itemList;
    public TextField idBox;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void dataClear(){
        idBox.clear();
    }
    public void searchById() {
        ItemsServices itemsServices = new ItemsServices();
        Item item = itemsServices.findItemByID(Integer.parseInt(idBox.getText()));
        if (item != null) {
            itemList.getItems().clear();
            itemList.getItems().add(item);
        } else {
            alert.setContentText(Constantes.ITEM_NOT_EXIST);
            alert.showAndWait();
        }
    }
}
