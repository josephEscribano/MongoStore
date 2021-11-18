package dao;

import dao.interfaces.*;
import dao.springJDBC.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactory {
    private static DAOFactory daoFactoryItem;

    private Properties daoProps;


    private String PROPERTIES_FILES = "config\\dao-properties.xml";

    public DAOFactory(){
        try {
            setDAOTypes(PROPERTIES_FILES);
        }catch (Exception e){
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    private void setDAOTypes(String confiFiles) throws IOException {
        daoProps = new Properties();
        daoProps.loadFromXML(Files.newInputStream(Paths.get(confiFiles)));
    }

    public DAOItems getDAOItems(){
        String sourceItems;
        DAOItems dao = null;
        sourceItems = daoProps.getProperty("daoItemsSpring");
        if (sourceItems.equals("SpringDAOItems")){
            dao = new SpringDAOItems();
        }


        return dao;
    }

    public DAOCustomers getDAOCustomers(){
        String sourceCustomers;
        DAOCustomers dao = null;
        sourceCustomers = daoProps.getProperty("daoCustomersSpring");
        if (sourceCustomers.equals("SpringDAOCustomers")){
            dao = new SpringDAOCustomers();
        }

        return dao;

    }

    public DAOPurchases getDAOPurchases(){
        String sourcesPurchses;
        DAOPurchases dao = null;
        sourcesPurchses = daoProps.getProperty("daoPurchasesSpring");
        if (sourcesPurchses.equals("SpringDAOPurchases")){
            dao = new SpringDAOPurchases();
        }

        return dao;
    }

    public DAOReviews getDAOReviews(){
        String sourcesPurchses;
        DAOReviews dao = null;
        sourcesPurchses = daoProps.getProperty("daoReviewsSpring");
        if (sourcesPurchses.equals("SpringDAOReviews")){
            dao = new SpringDAOReviews();
        }

        return dao;
    }

    public DAOUsers getDAOUsers(){
        String sourcesPurchses;
        DAOUsers dao = null;
        sourcesPurchses = daoProps.getProperty("daoUsersSpring");
        if (sourcesPurchses.equals("SpringDAOUsers")){
            dao = new SpringDAOUsers();
        }

        return dao;
    }


}
