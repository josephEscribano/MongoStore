/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Customer;
import model.Purchase;
import model.Review;

import java.sql.Date;
import java.util.List;

/**
 *
 */
public interface DAOPurchases {


    int getPurchasesByItemId(int id);

    int getPurchasesByReviewId(int id);
     
    List<Purchase> getAll();

    List<Purchase> getAllPurchaseForUser(int id);
     
    boolean save(Purchase t);
     
    int update(Purchase t);

    int searchCustomerByid(int id );
    int delete(int id );
    List<Purchase> findPurchaseByDate(java.util.Date date);
}
