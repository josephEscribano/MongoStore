/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import fx.controllers.purchases.*;
import fx.controllers.reviews.*;
import fx.controllers.customers.*;
import fx.controllers.items.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.xml.parsers.ParserConfigurationException;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    public MenuItem customerAdd;
    @FXML
    public MenuItem customerList;

    @FXML
    public MenuItem customerDelete;
    @FXML
    private MenuItem deletereviewitem;
    @FXML
    private MenuItem addReviews;
    @FXML
    private Menu items;
    @FXML
    private Menu customers;
    @FXML
    private Menu purchase;

    //Reference to the top menu to change its visibility when needed.
    @FXML
    private BorderPane fxRoot;

    public void setFxRoot(BorderPane fxRoot) {
        this.fxRoot = fxRoot;
    }

    @FXML
    private MenuBar fxMenuTop;
    // Get y set of the user to use it wherever we need it
    private String username;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // References to other panes to load them and access their controllers
    private AnchorPane login;
    private FXMLLoginController loginController;
    private AnchorPane welcome;
    private FXMLWelcomeController welcomeController;

    private AnchorPane purchases;
    private FXMLAddPurchasesController purchasesController;

    private AnchorPane delete;
    private FXMLDeleteController deleteController;

    private AnchorPane addCustomer;
    private FXMLAddCustomerController addCustomerController;

    private AnchorPane deleteCustomer;
    private FXMLdeleteCustomerController deleteCustomerController;

    private AnchorPane addReview;
    private FXMLAddReviewController addReviewController;

    private AnchorPane deleteReview;
    private FXMLdeleteReviewController deleteReviewController;

    private AnchorPane listItems;
    private FXMLListItemsController listItemsController;

    private AnchorPane additem;
    private FXMLAddItemsController additemscontroller;

    private AnchorPane deleteitem;
    private FXMLDeleteItemController deleteitemscontroller;

    private AnchorPane listCustomers;
    private FXMLListCustomerController listCustomerController;




    public void preloadLogin() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/FXMLLogin.fxml"));
            login = loaderMenu.load();
            loginController
                    = loaderMenu.getController();

            loginController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadWelcome() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/FXMLWelcome.fxml"));
            welcome = loaderMenu.load();
            welcomeController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadPurchases() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/purchases/FXMLAddPurchases.fxml"));
            purchases = loaderMenu.load();
            purchasesController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void preloadDelete() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/purchases/FXMLDelete.fxml"));
            delete = loaderMenu.load();
            deleteController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddCustomer() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLAddCustomer.fxml"));
            addCustomer = loaderMenu.load();
            addCustomerController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadDeleteCustomer() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLdeleteCustomer.fxml"));
            deleteCustomer = loaderMenu.load();
            deleteCustomerController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddReview() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reviews/FXMLaddReview.fxml"));
            addReview = loaderMenu.load();
            addReviewController = loaderMenu.getController();
            addReviewController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadDeleteReview() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reviews/FXMLdeleteReview.fxml"));
            deleteReview = loaderMenu.load();
            deleteReviewController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadListItems() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLListItems.fxml"));
            listItems = loaderMenu.load();
            listItemsController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadadditems() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLAdditems.fxml"));
            additem = loaderMenu.load();
            additemscontroller = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloaddeleteitems() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLDeleteItem.fxml"));
            deleteitem = loaderMenu.load();
            deleteitemscontroller = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadListCustomers() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLListCustomer.fxml"));
            listCustomers = loaderMenu.load();
            listCustomerController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }







    public void chargeLogin() {
        fxRoot.setCenter(login);
        fxMenuTop.setVisible(false);

    }
    public void chargeWelcome() {
        welcomeController.setLogin(this.getUsername());
        if (this.getIdUser() == 0){
            fxMenuTop.setVisible(true);
            items.setVisible(true);
            customers.setVisible(true);
            purchase.setVisible(true);
            deletereviewitem.setVisible(true);
            customerAdd.setVisible(true);
            customerDelete.setVisible(true);
            customerList.setVisible(true);
            addReviews.setVisible(false);
        }else{
            fxMenuTop.setVisible(true);
            items.setVisible(false);
            purchase.setVisible(false);
            deletereviewitem.setVisible(false);
            customerAdd.setVisible(false);
            customerDelete.setVisible(false);
            customerList.setVisible(false);
            addReviews.setVisible(true);
        }

        fxRoot.setCenter(welcome);
    }
    
    public void chargePurchases() {
        purchasesController.load();
        fxRoot.setCenter(purchases);
    }

    public void chargeDelete() {
        deleteController.loadPurchases();
        fxRoot.setCenter(delete);
    }

    public void chargeAddCustomer() throws ParserConfigurationException {
        addCustomerController.loadCustomersList();
        fxRoot.setCenter(addCustomer);
    }

    public void chargeDeleteCustomer() {
        deleteCustomerController.loadCustomersList();
        fxRoot.setCenter(deleteCustomer);
    }
    public void chargeListCustomer() throws ParserConfigurationException {
        listCustomerController.load();
        fxRoot.setCenter(listCustomers);
    }

    public void chargeAddReview() {
        addReviewController.loadPurchases();
        fxRoot.setCenter(addReview);
    }
    public void chargeDeleteReview() {
        deleteReviewController.loadCustomersList();
        fxRoot.setCenter(deleteReview);
    }


    public void listItems() {
        listItemsController.loadItemsList();
        fxRoot.setCenter(listItems);
    }

    public void chargeAddItems() {
        additemscontroller.loadItems();
        fxRoot.setCenter(additem);
    }

    public void chargeDeleteItems() {
        deleteitemscontroller.loadItems();
        fxRoot.setCenter(deleteitem);
    }




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preloadWelcome();
        preloadLogin();

        preloadPurchases();
        preloadDelete();

        preloadAddCustomer();
        preloadDeleteCustomer();

        preloadAddReview();
        preloadDeleteReview();
        preloadadditems();
        preloaddeleteitems();
        preloadListItems();
        preloadListCustomers();
        chargeLogin();

    }


}
