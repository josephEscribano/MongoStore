package fx.controllers.items;


import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Item;
import services.ItemsServices;
import utils.Constantes;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FXMLDeleteItemController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private final Alert confir = new Alert(Alert.AlertType.CONFIRMATION);
    public ListView<Item> lvlistItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void deleteItem() {
        Item it = lvlistItems.getSelectionModel().getSelectedItem();
        ItemsServices itemsServices = new ItemsServices();
        if (it != null) {
            int result = itemsServices.checkDelete(it.getIdItem(), false);
            if (result == -1) {
                alert.setContentText(Constantes.EXIST_REVIEWS);
                alert.showAndWait();
            } else if (result == -3) {
                confir.setTitle(Constantes.TITLE_MESSAGE);
                confir.setContentText(Constantes.NOTICE_DELETED);
                Optional<ButtonType> action = confir.showAndWait();
                if (action.get() == ButtonType.OK) {
                    if (itemsServices.checkDelete(it.getIdItem(), true) == -2){
                        alert.setContentText(Constantes.ITEM_NOT_DELETED);
                        alert.showAndWait();
                    }else{
                        lvlistItems.getItems().remove(it);
                    }


                }
            } else if (result == -5) {
                lvlistItems.getItems().remove(it);
            }else if (result == -2){
                alert.setContentText(Constantes.ITEM_NOT_DELETED);
                alert.showAndWait();
            }

        } else {
            alert.setContentText(Constantes.SELECT_ITEM);
            alert.showAndWait();
        }
    }


    public void loadItems() {
        ItemsServices is = new ItemsServices();
        lvlistItems.getItems().clear();
        lvlistItems.getItems().addAll(is.getAll());
    }
}
