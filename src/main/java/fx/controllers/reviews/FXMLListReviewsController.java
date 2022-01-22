package fx.controllers.reviews;

import fx.controllers.FXMLModalInfoReviews;
import fx.controllers.FXMLPrincipalController;
import io.vavr.control.Either;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Review;
import services.CustomersServices;
import services.ItemsServices;
import services.ReviewsServices;
import utils.Constantes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class FXMLListReviewsController {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ListView<Review> reviewList;
    @FXML
    private  ComboBox<String> cbSelected;
    @FXML
    private  ComboBox<Item> cbiItem;
    @FXML
    private  ComboBox<Customer> cbCustomer;
    @FXML
    private  ComboBox<Integer> cbRaiting;
    private FXMLPrincipalController principal;


    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void load() {
        ReviewsServices reviewsServices = new ReviewsServices();
        cbSelected.getItems().setAll("Item","Customer","Raiting");
        if (principal.getIdUser() > 0){
            reviewList.getItems().setAll(reviewsServices.getALLReviewsByUser(principal.getIdUser()));
        }else{
            reviewList.getItems().setAll(reviewsServices.getAllReviews());
        }

    }


    public void showData() {
        ItemsServices itemsServices = new ItemsServices();
        CustomersServices customersServices = new CustomersServices();

        String selected = cbSelected.getSelectionModel().getSelectedItem();

        if (selected.equals("Item")){
            cbiItem.getItems().setAll(itemsServices.getAll());
            cbiItem.setConverter(new StringConverter<Item>() {
                @Override
                public String toString(Item item) {
                    return item == null ? null : item.getName();
                }

                @Override
                public Item fromString(String s) {
                    return null;
                }
            });
            cbiItem.setVisible(true);
            cbCustomer.setVisible(false);
            cbRaiting.setVisible(false);



        }else if (selected.equals("Customer")){
            cbCustomer.getItems().setAll(customersServices.getAllCustomers());
            cbCustomer.setConverter(new StringConverter<Customer>() {
                @Override
                public String toString(Customer customer) {
                    return customer == null ? null : customer.getName();
                }

                @Override
                public Customer fromString(String s) {
                    return null;
                }
            });

            cbiItem.setVisible(false);
            cbCustomer.setVisible(true);
            cbRaiting.setVisible(false);


        }else{
            for (int i  = 0; i <= 5; i++){
                cbRaiting.getItems().add(i);
            }
            cbiItem.setVisible(false);
            cbCustomer.setVisible(false);
            cbRaiting.setVisible(true);


        }
    }

    public void chargeReviewsByItem() {
        ReviewsServices reviewsServices = new ReviewsServices();

        Item item = cbiItem.getSelectionModel().getSelectedItem();
        if(item != null){
            reviewList.getItems().setAll(reviewsServices.getReviewByItem(item.getIdItem()));
        }else{
            alert.setContentText(Constantes.SELECT_ITEM);
            alert.showAndWait();
        }

    }

    public void chargeReviewsByCustomer() {
        ReviewsServices reviewsServices = new ReviewsServices();


        Customer customer = cbCustomer.getSelectionModel().getSelectedItem();

        if (customer != null){
            reviewList.getItems().setAll(reviewsServices.getALLReviewsByUser(customer.getIdCustomer()));
        }else{
            alert.setContentText(Constantes.SELECT_CUSTOMER);
            alert.showAndWait();
        }
    }

    public void chargeReviewsByRaiting() {
        ReviewsServices reviewsServices = new ReviewsServices();

        Integer raiting = cbRaiting.getSelectionModel().getSelectedItem();

        if (raiting != null){
            Either<String,List<Review>> result = reviewsServices.reviewsByRaiting(raiting);
            if (result.isRight()){
                reviewList.getItems().setAll(result.get());
            }else{
                alert.setContentText(result.getLeft());
                alert.showAndWait();
            }

        }else{
            alert.setContentText(Constantes.PLEASE_SELECT_A_RAITING);
            alert.showAndWait();
        }
    }

    public void chargeInfo() {

        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane pantallaInfo;
        FXMLModalInfoReviews fxmlModalInfoReviews;

        Review review = reviewList.getSelectionModel().getSelectedItem();
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
}
