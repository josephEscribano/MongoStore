/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Customer;
import model.Review;
import services.CustomersServices;
import services.ReviewsServices;
import utils.Constantes;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLdeleteReviewController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ListView<Customer> customerBox;
    @FXML
    private ListView<Review> reviewBox;

    public void loadReviewsList() {
        ReviewsServices reviewsServices = new ReviewsServices();
        Customer customer = customerBox.getSelectionModel().getSelectedItem();
        if (customer != null){
            List<Review> list = reviewsServices.searchByCustomer(customer.getIdCustomer());
            if (!list.isEmpty()){
                reviewBox.getItems().setAll(list);
            }else{
                alert.setContentText(Constantes.REVIEW_NOT_EXIST_CUSTOMER);
                alert.showAndWait();
            }
        }else{
            alert.setContentText(Constantes.SELECT_CUSTOMER);
            alert.showAndWait();
        }
     }

    public void loadCustomersList() {
        CustomersServices customersServices = new CustomersServices();
        customerBox.getItems().setAll(customersServices.getAllCustomers());

    }

    public void deleteReview() {
        ReviewsServices reviewsServices = new ReviewsServices();
        Review review = reviewBox.getSelectionModel().getSelectedItem();
        if (review != null){
            if (reviewsServices.deleteReview(review) > 0){
                reviewBox.getItems().removeIf(it -> it.getIdReview() == review.getIdReview());
            }else{
                alert.setContentText(Constantes.REVIEW_NOT_EXIST);
                alert.showAndWait();
            }
        }else{
            alert.setContentText(Constantes.SELECT_CUSTOMER);
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
