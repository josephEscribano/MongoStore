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

public class PurchasesServices {
    private DAOFactory dao;


    public PurchasesServices() {
        dao = new DAOFactory();
    }
    public int updatePurchases(Purchase purchase){
        return dao.getDAOPurchases().update(purchase);
    }
    public List<Purchase> getAllPurchases() {
        return dao.getDAOPurchases().getAll();
    }

    public List<Purchase> getPurchasesForUser(int id){
        return dao.getDAOPurchases().getAllPurchaseForUser(id);
    }
    public int getPurchasesByReviewId(int id){
        return dao.getDAOPurchases().getPurchasesByReviewId(id);
    }

    public int deletePurchase(int id ) {
        int confirmacion = -1;
        //Si confirmacion es -1 quiere decir que tiene reviews asociadas.
        if (getPurchasesByReviewId(id) == 0){
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
