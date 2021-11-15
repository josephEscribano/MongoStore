package fx.controllers.reviews;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Review;
import services.ReviewsServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListReviewsController implements Initializable {
    public ListView<Review> reviewList;

    public void load() {
        ReviewsServices reviewsServices = new ReviewsServices();
        reviewList.getItems().setAll(reviewsServices.getAllReviews());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
