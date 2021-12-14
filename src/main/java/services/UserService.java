package services;

import dao.DAOFactory;
import model.User;

public class UserService {

    private DAOFactory dao;

    public UserService() {
        this.dao = new DAOFactory();
    }

    public User checkUser(String name, String password){
        return dao.getDAOUsers().checkUser(name,password);
    }

    public int checkUserByName(String name){
        return dao.getDAOUsers().checkUserByName(name);
    }
}
