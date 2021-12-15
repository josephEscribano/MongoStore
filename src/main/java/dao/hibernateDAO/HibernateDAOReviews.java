package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOReviews;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Purchase;
import model.Review;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateQuerys;

import java.util.List;

@Log4j2
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
        Session session = null;
        List<Review> list = null;
        try{
            session = HibernateUtils.getSession();
            list = session.createQuery(HibernateQuerys.FROM_REVIEW_,Review.class).list();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }

        }

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
