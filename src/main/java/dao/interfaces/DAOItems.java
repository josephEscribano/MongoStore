/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import model.Item;

import java.util.List;

/**
 *
 */
public interface DAOItems {

     
    List<Item> getAll();
     
    boolean save(Item t);
     
    int update(Item t);
     
    int deletePurchasesAndItem(int id);

    int deleteItem(int id);

    Item findItemByID(int id);


    void closePool();

    int checkItemReview(int id);
}
