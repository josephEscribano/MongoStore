/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.items;

import fx.controllers.FXMLModalInfoReviews;
import io.vavr.control.Either;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import model.Item;
import model.Review;
import services.ItemsServices;
import services.ReviewsServices;
import utils.Constantes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Log4j2
public class FXMLListItemsController{
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ComboBox<String> cbOrder;
    @FXML
    private  ListView<Review> lvReviews;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfMonth;
    @FXML
    private TextField tfRating;
    @FXML
    private Label labelMonth;
    @FXML
    private ListView<Item> itemsList;

    public void loadItemsList() {
        ItemsServices itemsServices = new ItemsServices();
        lvReviews.getItems().clear();

        itemsList.getItems().setAll(itemsServices.getAll());
        cbOrder.getItems().setAll("Date/Ascendant","Date/Descendant","Raiting/Acendant","Raiting/Descendant");
    }



    public void showData() {
        ItemsServices itemsServices = new ItemsServices();

        Item item = itemsList.getSelectionModel().getSelectedItem();
        if (item != null){
            tfPrice.setText(String.valueOf(item.getPrice()));
            labelMonth.setText(String.valueOf(LocalDate.now().getMonth()).toLowerCase());
            Either<String, Integer> result = itemsServices.numbersPurchasesByMonth(LocalDate.now().getMonthValue(),LocalDate.now().getYear(),item.getIdItem());
            if (result.isRight()){
                tfMonth.setText(String.valueOf(result.get()));
            }else {
                alert.setContentText(result.getLeft());
                alert.showAndWait();
            }

//            check this because if there are no reviews it throws a nullpointerException

            if (itemsServices.countReviewsByItem(item.getIdItem()) > 0){
                Either<String, Integer> resultAVG = itemsServices.averageRaitingByItem(item.getIdItem());
                if (result.isRight()){
                    tfRating.setText(String.valueOf(resultAVG.get()));
                }else{
                    alert.setContentText(result.getLeft());
                    alert.showAndWait();
                }
            }else{
                tfRating.setText("0");
            }
            List<Review> list = itemsServices.searchByItem(item.getIdItem());
            lvReviews.getItems().setAll(list);




        }
    }

    public void showInfo() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane pantallaInfo;
        FXMLModalInfoReviews fxmlModalInfoReviews;

        Review review = lvReviews.getSelectionModel().getSelectedItem();
        try {
            if (review !=null){
                pantallaInfo = fxmlLoader.load(getClass().getResourceAsStream("/fxml/FXMLModalInfoReviews.fxml"));
                fxmlModalInfoReviews = fxmlLoader.getController();
                fxmlModalInfoReviews.chargeInfoReviews(review);

                Stage stage = new Stage();
                stage.setScene(new Scene(pantallaInfo));
                stage.show();
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            alert.setContentText(Constantes.ERROR_PANTALLA);
            alert.showAndWait();
        }

    }

    public void orderByRevies() {

        ReviewsServices reviewsServices = new ReviewsServices();
        Item item = itemsList.getSelectionModel().getSelectedItem();
        if (cbOrder.getValue().equals("Date/Ascendant")){
            Either<String,List<Review>> resultDateAsc = reviewsServices.reviewsOrderByDateAsc(item.getIdItem());
            if (resultDateAsc.isRight()){
                lvReviews.getItems().setAll(resultDateAsc.get());
            }else{
                alert.setContentText(resultDateAsc.getLeft());
                alert.showAndWait();
            }
        }else if (cbOrder.getValue().equals("Date/Descendant") ){
            Either<String,List<Review>> resultDateDesc = reviewsServices.reviewsOrderByDateDesc(item.getIdItem());
            if (resultDateDesc.isRight()){
                lvReviews.getItems().setAll(resultDateDesc.get());
            }else{
                alert.setContentText(resultDateDesc.getLeft());
                alert.showAndWait();
            }
        }else if (cbOrder.getValue().equals("Raiting/Acendant") ){
            Either<String,List<Review>> resultRaitingAsc = reviewsServices.reviewsOrderByRaitingAsc(item.getIdItem());
            if (resultRaitingAsc.isRight()){
                lvReviews.getItems().setAll(resultRaitingAsc.get());
            }else{
                alert.setContentText(resultRaitingAsc.getLeft());
                alert.showAndWait();
            }
        }else if (cbOrder.getValue().equals("Raiting/Descendant")){
            Either<String,List<Review>> resultRaitingDesc = reviewsServices.reviewsOrderByRaitingDesc(item.getIdItem());
            if (resultRaitingDesc.isRight()){
                lvReviews.getItems().setAll(resultRaitingDesc.get());
            }else{
                alert.setContentText(resultRaitingDesc.getLeft());
                alert.showAndWait();
            }
        }
    }
}
