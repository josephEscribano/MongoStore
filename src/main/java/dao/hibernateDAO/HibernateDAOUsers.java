package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOUsers;
import lombok.extern.log4j.Log4j2;
import model.User;
import org.hibernate.Session;
@Log4j2
public class HibernateDAOUsers implements DAOUsers {
    @Override
    public User checkUser(String name, String password) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtils.getSession();
            user = session.createNamedQuery("findUser",User.class)
                    .setParameter("name",name)
                    .setParameter("pass",password)
                    .uniqueResult();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }

        return user;
    }

    @Override
    public int checkUserByName(String name) {
        int confimacion = 1;
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            String query = "select count(*) from User where name = :name";
            confimacion= (session.createQuery(query,Long.class).setParameter("name",name).uniqueResult()).intValue();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }

        return confimacion;
    }
}
