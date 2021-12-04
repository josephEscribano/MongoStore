package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOReviews;
import model.Customer;
import model.Review;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateQuerys;

import java.util.List;

public class HibernateDAOReviews implements DAOReviews {

    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List<Review> getReviewByItem(int id) {
        return null;
    }

    @Override
    public List<Review> getReviewByItemByUser(int idUser, int idItem) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        Session session;
        List<Review> list;
        session = HibernateUtils.getSession();
        Query query = session.createQuery(HibernateQuerys.FROM_REVIEW_);
        list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Review> getAllByUser(int id) {
        return null;
    }

    @Override
    public boolean save(Review t) {
        return false;
    }

    @Override
    public int update(Review t) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Review> getReviewByCustomer(int id) {
        return null;
    }
}
