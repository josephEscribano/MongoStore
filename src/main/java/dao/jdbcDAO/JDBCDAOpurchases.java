package dao.jdbcDAO;

import dao.DAOPurchases;
import dao.DBConection;
import model.Customer;
import model.Item;
import model.Purchase;
import model.Review;
import utils.Querys;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class JDBCDAOpurchases implements DAOPurchases {


    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private DBConection db;

    public JDBCDAOpurchases(){
        db = new DBConection();
    }
    @Override
    public Purchase get(int id) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByItemId(int id) {
        List<Purchase> list = null;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_PURCHASES_BY_ITEM_QUERY);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            list = builder(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<Purchase> getPurchasesByReviewId(int id) {
        List<Purchase> list = null;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_PURCHASE_IN_REVIEW_QUERY);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            list = builder(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return list;
    }


    @Override
    public List<Purchase> searchCustomerByid(int id) {
        List<Purchase> list = null;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_PURCHASES_BY_CUSTOMER_QUERY);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            list = builder(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return list;
    }


    @Override
    public List<Purchase> getAll() {
        List<Purchase> list = null;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_PURCHASES_QUERY);
            resultSet = preparedStatement.executeQuery();

            list = builder(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }

        return list;
    }

    @Override
    public boolean save(Purchase purchase) {
        boolean confirmacion = false;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.INSERT_PURCHASE_QUERY,Statement.RETURN_GENERATED_KEYS);
            java.util.Date date =  Date.from(purchase.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
            preparedStatement.setInt(2,purchase.getCustomer().getIdCustomer());
            preparedStatement.setInt(3,purchase.getItem().getIdItem());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            int auto_id = 0;
            if (resultSet.next()){
                auto_id = resultSet.getInt(1);
            }
            purchase.setIdPurchase(auto_id);
            confirmacion = true;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return confirmacion;
    }

    @Override
    public boolean update(Purchase purchase) {
        boolean confirmacion = false;
        try {
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.UPDATE_PURCHASES_QUERY);
            java.util.Date date =  Date.from(purchase.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            preparedStatement.setDate(1,new java.sql.Date(date.getTime()));
            preparedStatement.setInt(2,purchase.getIdPurchase());

            preparedStatement.executeUpdate();
            confirmacion = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return confirmacion;

    }

    @Override
    public boolean delete(Purchase purchase) {
        boolean confirmacion = false;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.DELETE_PURCHASE_QUERY);
            preparedStatement.setInt(1,purchase.getIdPurchase());
            preparedStatement.executeUpdate();
            confirmacion = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }

        return confirmacion;
    }

    public List<Purchase> findPurchaseByDate(java.util.Date date){
        List<Purchase> list = null;
        try {
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_PURCHASE_BY_DATE_QUERY);
            preparedStatement.setDate(1,new java.sql.Date(date.getTime()));
            resultSet = preparedStatement.executeQuery();

            list = builder(resultSet);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return list;
    }

    private List<Purchase> builder(ResultSet resultSet){
        Purchase purchase ;
        List<Purchase> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                purchase = new Purchase(resultSet.getInt(1)
                        ,new Customer(resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6))
                        ,new Item(resultSet.getInt(7),resultSet.getString(8),resultSet.getString(9),resultSet.getDouble(10))
                        ,new java.sql.Date(resultSet.getDate(2).getTime()).toLocalDate());
                list.add(purchase);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}
