package dao.interfaces;

import model.User;

public interface DAOUsers {

    User checkUser(String name, String password);
    int checkUserByName(String name);
}
