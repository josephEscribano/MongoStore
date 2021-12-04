package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOItems;
import model.Customer;
import model.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateQuerys;

import java.util.List;

public class HibernateDAOItems implements DAOItems {

    @Override
    public List<Item> getAll() {
        Session session;
        List<Item> list;
        session = HibernateUtils.getSession();
        Query query = session.createQuery(HibernateQuerys.FROM_ITEM_);
        list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean save(Item t) {
        return false;
    }

    @Override
    public int update(Item t) {
        return 0;
    }

    @Override
    public int deletePurchasesAndItem(int id) {
        return 0;
    }

    @Override
    public int deleteItem(int id) {
        return 0;
    }

    @Override
    public Item findItemByID(int id) {
        return null;
    }

    @Override
    public int checkItemReview(int id) {
        return 0;
    }
}
