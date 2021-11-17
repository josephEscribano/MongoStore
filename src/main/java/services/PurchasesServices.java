/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;

import dao.DAOFactory;
import model.Purchase;

/**
 *
 * @author dam2
 */
public class PurchasesServices {
    private DAOFactory dao;


    public ArrayList<Purchase> searchByDate(String date) {
        ArrayList<Purchase> purch =  null;
        return purch;
    }

    public PurchasesServices() {
        dao = new DAOFactory();
    }
    public int updatePurchases(Purchase purchase){
        return dao.getDAOPurchases().update(purchase);
    }
    public List<Purchase> getAllPurchases() {
        return dao.getDAOPurchases().getAll();
    }



    public int getPurchasesByClientId(int id) {
        return dao.getDAOPurchases().searchCustomerByid(id);
    }

    public int getPurchasesByItemId(int id) {

        return dao.getDAOPurchases().getPurchasesByItemId(id);
    }

    public List<Purchase> getPurchasesByReviewId(int id){
        return dao.getDAOPurchases().getPurchasesByReviewId(id);
    }

    public int deletePurchase(int id ) {
        int confirmacion = -1;
        //Si confirmacion es -1 quiere decir que tiene reviews asociadas.
        if (getPurchasesByReviewId(id).isEmpty()){
            confirmacion = dao.getDAOPurchases().delete(id);
        }
        return confirmacion;
     }

    public boolean addPurchase(Purchase purchase) {
        return dao.getDAOPurchases().save(purchase);

    }

    public List<Purchase> findPurchaseByDate(java.util.Date date){
        return dao.getDAOPurchases().findPurchaseByDate(date);
    }

}
