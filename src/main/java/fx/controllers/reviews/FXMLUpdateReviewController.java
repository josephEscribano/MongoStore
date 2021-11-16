package fx.controllers.reviews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Review;
import services.ReviewsServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLUpdateReviewController implements Initializable {
    public TextArea textBox;
    public ListView<Review> reviewList;
    public ComboBox<Integer> ratingBox;
    public TextField titleBox;

    public void updateReview(ActionEvent actionEvent) {
        Review review = reviewList.getSelectionModel().getSelectedItem();
        if (review != null){
            textBox.setText(review.getDescription());
            titleBox.setText(review.getTitle());
            ratingBox.setValue(review.getRating());
        }
    }
    public void loadUpdate() {
        ReviewsServices reviewsServices = new ReviewsServices();
        reviewList.getItems().setAll(reviewsServices.getAllReviews());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
