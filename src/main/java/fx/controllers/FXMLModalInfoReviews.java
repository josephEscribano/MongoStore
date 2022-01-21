package fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Review;

public class FXMLModalInfoReviews {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfRaiting;
    @FXML
    private TextArea taDescription;


    public void chargeInfoReviews(Review review){
        tfTitle.setText(review.getTitle());
        taDescription.setText(review.getDescription());
        tfRaiting.setText(String.valueOf(review.getRating()));
        tfDate.setText(String.valueOf(review.getDate()));
    }
}
