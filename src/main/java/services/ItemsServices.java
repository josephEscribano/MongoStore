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

    public int checkItemReviews(int id){
        return dao.getDAOItems().checkItemReview(id);
    }
    public int checkDelete(int id,boolean confirm){
        int confirmacion = -5;
        int resultado = dao.getDAOItems().checkItemReview(id);
        int resultadoPurchases = dao.getDAOPurchases().getPurchasesByItemId(id);
        if (resultado > 0){
            confirmacion = -1;
        }else if(resultadoPurchases > 0 && !confirm){
            confirmacion = -2;
        }else if (resultadoPurchases > 0 && confirm){
            confirmacion = -3;
            dao.getDAOItems().deletePurchasesAndItem(id);
        } else if(resultadoPurchases == 0){
            confirmacion = -4;
            dao.getDAOItems().deleteItem(id);
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
