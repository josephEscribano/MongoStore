/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import model.Purchase;

import java.util.List;

public class PurchasesServices {
    private final DAOFactory dao;


    public PurchasesServices() {
        dao = new DAOFactory();
    }

    public int updatePurchases(Purchase purchase) {
        return dao.getDAOPurchases().update(purchase);
    }

    public List<Purchase> getAllPurchases() {
        return dao.getDAOPurchases().getAll();
    }

    public List<Purchase> getPurchasesForUser(int id) {
        return dao.getDAOPurchases().getAllPurchaseForUser(id);
    }

    public int getPurchasesByReviewId(int id) {
        return dao.getDAOPurchases().getPurchasesByReviewId(id);
    }

    public int deletePurchase(Purchase purchase) {
        int confirmacion = -1;
        //Si confirmacion es -1 quiere decir que tiene reviews asociadas.
        if (getPurchasesByReviewId(purchase.getIdPurchase()) == 0) {
            confirmacion = dao.getDAOPurchases().delete(purchase);
        }
        return confirmacion;
    }

    public boolean savePurchase(Purchase purchase) {
        return dao.getDAOPurchases().save(purchase);

    }

    public List<Purchase> findPurchaseByDate(java.util.Date date) {
        return dao.getDAOPurchases().findPurchaseByDate(date);
    }

}
