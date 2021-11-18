package dao;

public interface DAOUsers {

    int checkUser(String name, String password);
    int checkUserByName(String name);
}
