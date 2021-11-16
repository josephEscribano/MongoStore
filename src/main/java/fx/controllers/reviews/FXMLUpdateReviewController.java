package fx.controllers.reviews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Review;
import services.ReviewsServices;
import utils.Constantes;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FXMLUpdateReviewController implements Initializable {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public TextArea textBox;
    public ListView<Review> reviewList;
    public ComboBox<Integer> ratingBox;
    public TextField titleBox;

    public void showInfo() {
        Review review = reviewList.getSelectionModel().getSelectedItem();
        if (review != null){
            textBox.setText(review.getDescription());
            titleBox.setText(review.getTitle());
            ratingBox.setValue(review.getRating());
        }
    }

    public void chargeRating(){
        for (int i  = 0; i <= 5; i++){
            ratingBox.getItems().add(i);
        }
    }

    public void updateReview() {
        ReviewsServices reviewsServices = new ReviewsServices();
        Review review = reviewList.getSelectionModel().getSelectedItem();
        if (review != null){
            review.setRating(ratingBox.getValue());
            review.setTitle(titleBox.getText());
            review.setDescription(textBox.getText());
            review.setDate(LocalDate.now());
            if (reviewsServices.updateReview(review) > 0){
                for(int i  = 0; i < reviewList.getItems().size(); i++){
                    if (reviewList.getItems().get(i) == review){
                        reviewList.getItems().set(i,review);
                    }
                }
            }else{
                alert.setContentText(Constantes.REVIEW_NOT_UPDATE);
                alert.showAndWait();
            }
        }else{
            alert.setContentText(Constantes.SELECT_REVIEW);
            alert.showAndWait();
        }
        }

    public void loadUpdate() {
        ReviewsServices reviewsServices = new ReviewsServices();
        reviewList.getItems().setAll(reviewsServices.getAllReviews());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chargeRating();
    }


}
