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


    public boolean save(Item item){
        return dao.getDAOItems().save(item);

    }

    public int checkDelete(int id,boolean confirm){
        int confirmacion = -6;
        int resultado = dao.getDAOItems().checkItemReview(id);
        int resultadoPurchases = dao.getDAOPurchases().getPurchasesByItemId(id);
        if (resultado > 0){
            confirmacion = -1;
        }else if(resultadoPurchases > 0 && !confirm){
            confirmacion = -3;
        }else if (resultadoPurchases > 0 && confirm){
            if (dao.getDAOItems().deletePurchasesAndItem(id) == -2){
                confirmacion = dao.getDAOItems().deletePurchasesAndItem(id);
            }else{
                confirmacion = -4;
            }

        } else if(resultadoPurchases == 0){
            if ( dao.getDAOItems().deleteItem(id) == -2){
                confirmacion =  dao.getDAOItems().deleteItem(id);
            }else{
                confirmacion = -5;
            }

        }


        return confirmacion;
    }




    public Item findItemByID(int id){
        return dao.getDAOItems().findItemByID(id);
    }

}
