package fx.controllers.reviews;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Review;
import services.ReviewsServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListReviewsController implements Initializable {
    @FXML
    public ListView<Review> reviewList;
    private FXMLPrincipalController principal;


    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }
    public void load() {
        ReviewsServices reviewsServices = new ReviewsServices();
        if (principal.getIdUser() > 0){
            reviewList.getItems().setAll(reviewsServices.getALLReviewsByUser(principal.getIdUser()));
        }else{
            reviewList.getItems().setAll(reviewsServices.getAllReviews());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
