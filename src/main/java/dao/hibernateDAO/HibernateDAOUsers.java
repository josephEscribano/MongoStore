package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOUsers;
import model.User;
import org.hibernate.Session;

public class HibernateDAOUsers implements DAOUsers {
    @Override
    public User checkUser(String name, String password) {
        Session session = HibernateUtils.getSession();
        User user = session.createNamedQuery("findUser",User.class)
                .setParameter("name",name)
                .setParameter("pass",password)
                .uniqueResult();
        return user;
    }

    @Override
    public int checkUserByName(String name) {
        return 0;
    }
}
