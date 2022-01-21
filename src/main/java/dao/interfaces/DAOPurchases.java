/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import io.vavr.control.Either;
import model.Customer;
import model.Item;
import model.Purchase;
import model.Review;

import java.sql.Date;
import java.time.LocalDate;
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

    int delete(Purchase purchase );

    List<Purchase> findPurchaseByDate(LocalDate localDate);

    List<Purchase> purchasesBetweenDates(LocalDate initialDate, LocalDate finalDate);

    List<Purchase> getPurchasesByItem(int id);

    Either<String, Integer> numbersPurchasesByMonth(int mes,int year, int id);
}
