package dao.mongoDAO;

import dao.interfaces.DAOUsers;
import model.User;

public class UserDAO implements DAOUsers {
    @Override
    public User checkUser(String name, String password) {
        User user = new User();
        return user;
    }

    @Override
    public int checkUserByName(String name) {
        return 0;
    }
}
