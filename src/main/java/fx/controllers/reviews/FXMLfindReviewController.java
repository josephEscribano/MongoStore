/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Item;
import model.Review;
import services.ItemsServices;
import services.ReviewsServices;
import utils.Constantes;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLfindReviewController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ListView<Review> reviewList;
    @FXML
    private ComboBox<Item> itemBox;
    private FXMLPrincipalController principal;


    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void loadItems() {
        ItemsServices itemsServices = new ItemsServices();
        reviewList.getItems().clear();
        itemBox.getItems().setAll(itemsServices.getAll());
    }

    public void searchByItem() {
        ReviewsServices reviewsServices = new ReviewsServices();
        reviewList.getItems().clear();
        Item item = itemBox.getSelectionModel().getSelectedItem();

        if (item != null){
            List<Review> listReview ;
            if (principal.getIdUser() > 0){
                listReview = reviewsServices.getReviewsByItemByUser(principal.getIdUser(), item.getIdItem());
            }else{
                listReview = reviewsServices.searchByItem(item.getIdItem());
            }

            if (!listReview.isEmpty()){
                reviewList.getItems().setAll(listReview);
            }else{
                alert.setContentText(Constantes.REVIEW_NOT_EXIST_ITEM);
                alert.showAndWait();
            }
        }else{
            alert.setContentText(Constantes.SELECT_ITEM);
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
