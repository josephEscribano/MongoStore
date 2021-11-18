package services;

import dao.DAOFactory;
import dao.DAOUsers;

public class UserService {

    private DAOFactory dao;

    public UserService() {
        this.dao = new DAOFactory();
    }

    public int checkUser(String name, String password){
        return dao.getDAOUsers().checkUser(name,password);
    }

    public int checkUserByName(String name){
        return dao.getDAOUsers().checkUserByName(name);
    }
}
