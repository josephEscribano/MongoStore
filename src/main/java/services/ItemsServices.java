/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

import dao.DAOFactory;
import model.Item;
import model.Purchase;

/**
 *
 * @author dam2
 */
public class ItemsServices {

    private DAOFactory dao;

    public ItemsServices(){
        dao = new DAOFactory();
    }

    public int updateItem(Item item){
        return dao.getDAOItems().update(item);
    }
    public List<Item> getAll(){
        return dao.getDAOItems().getAll();
    }
    public Item get(int id){
        return dao.getDAOItems().get(id);
    }

    public boolean save(Item item){
        return dao.getDAOItems().save(item);

    }
    public int deletePurchasesAndItem(Item it){
        return dao.getDAOItems().deletePurchasesAndItem(it.getIdItem());
    }

    public int deleteItem(int id){
        return dao.getDAOItems().deleteItem(id);
    }

    public int checkDelete(int id){
        int confirmacion = -5;
        List<Purchase> list = dao.getDAOPurchases().getPurchasesByItemId(id);
        if (list.isEmpty()){
            confirmacion = dao.getDAOItems().deleteItem(id);
        }else{
            for (Purchase purchase: list){
                if (!dao.getDAOPurchases().getPurchasesByReviewId(purchase.getIdPurchase()).isEmpty()){
                    confirmacion = -3;
                }
            }
            if (confirmacion != -3){
                confirmacion = dao.getDAOItems().deletePurchasesAndItem(id);
            }
        }

        return confirmacion;
    }




    public Item findItemByID(int id){
        return dao.getDAOItems().findItemByID(id);
    }

    public void closePool(){
        dao.getDAOItems().closePool();
    }
}
