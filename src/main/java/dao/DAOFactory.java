package dao;

import dao.interfaces.*;
import dao.mongoDAO.*;

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
        sourceItems = daoProps.getProperty("daoItemsHibernate");
        if (sourceItems.equals("HibernateDAOItems")){
            dao = new ItemDAO();
        }


        return dao;
    }

    public DAOCustomers getDAOCustomers(){
        String sourceCustomers;
        DAOCustomers dao = null;
        sourceCustomers = daoProps.getProperty("daoCustomersHibernate");
        if (sourceCustomers.equals("HibernateDAOCustomers")){
            dao = new CustomerDAO();
        }

        return dao;

    }

    public DAOPurchases getDAOPurchases(){
        String sourcesPurchses;
        DAOPurchases dao = null;
        sourcesPurchses = daoProps.getProperty("daoPurchasesHibernate");
        if (sourcesPurchses.equals("HibernateDAOPurchases")){
            dao = new PurchaseDAO();
        }

        return dao;
    }

    public DAOReviews getDAOReviews(){
        String sourcesPurchses;
        DAOReviews dao = null;
        sourcesPurchses = daoProps.getProperty("daoReviewsHibernate");
        if (sourcesPurchses.equals("HibernateDAOReviews")){
            dao = new ReviewDAO();
        }

        return dao;
    }

    public DAOUsers getDAOUsers(){
        String sourcesPurchses;
        DAOUsers dao = null;
        sourcesPurchses = daoProps.getProperty("daoUsersHibernate");
        if (sourcesPurchses.equals("HibernateDAOUsers")){
            dao = new UserDAO();
        }

        return dao;
    }


}
