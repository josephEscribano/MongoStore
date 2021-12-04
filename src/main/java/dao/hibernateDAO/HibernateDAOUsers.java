package dao.hibernateDAO;

import dao.interfaces.DAOUsers;

public class HibernateDAOUsers implements DAOUsers {
    @Override
    public int checkUser(String name, String password) {
        return 0;
    }

    @Override
    public int checkUserByName(String name) {
        return 0;
    }
}
