package dao.interfaces;

public interface DAOUsers {

    int checkUser(String name, String password);
    int checkUserByName(String name);
}
