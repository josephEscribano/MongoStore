/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Customer;
import model.Purchase;
import model.Review;
import services.CustomersServices;
import services.PurchasesServices;
import services.ReviewsServices;
import utils.Constantes;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLAddReviewController implements Initializable {

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ListView<Purchase> purchaseBox;
    @FXML
    private ComboBox<Integer> ratingBox;
    @FXML
    private TextField titleBox;
    @FXML
    private TextArea textBox;

    public void loadCustomers() {

    }

     public void loadPurchases() {
        PurchasesServices purchasesServices = new PurchasesServices();
        purchaseBox.getItems().setAll(purchasesServices.getAllPurchases());

     }

     public void chargeRating(){
        for (int i  = 0; i <= 5; i++){
            ratingBox.getItems().add(i);
        }
     }

    public void addReview() {
        ReviewsServices reviewsServices = new ReviewsServices();
        Purchase purchase = purchaseBox.getSelectionModel().getSelectedItem();
        Integer num = ratingBox.getSelectionModel().getSelectedItem();
        String title = titleBox.getText();
        String description = textBox.getText();

        if (purchase != null && num != null && !titleBox.getText().isEmpty() && !textBox.getText().isEmpty()){
            Review review = new Review(num,title,description, LocalDate.now(),purchase);
            if (reviewsServices.addReview(review)){
                alert.setContentText(Constantes.REVIEW_ADDED);
                alert.showAndWait();
            }else{
                alert.setContentText(Constantes.REVIEW_NOT_ADDED);
                alert.showAndWait();
            }
        }else{
            alert.setContentText(Constantes.NOTICE_FIELDS);
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chargeRating();
    }

}
