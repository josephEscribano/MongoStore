package dao.mongoDAO;

import dao.interfaces.DAOReviews;
import io.vavr.control.Either;
import model.Review;

import java.util.List;

public class ReviewDAO implements DAOReviews {
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
        return null;
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
    public int delete(Review review) {
        return 0;
    }

    @Override
    public List<Review> getReviewByCustomer(int id) {
        return null;
    }

    @Override
    public int checkItemReview(int id) {
        return 0;
    }

    @Override
    public Either<String, Integer> averageRaitingByItem(int id) {
        return null;
    }

    @Override
    public double countReviewsByItem(int id) {
        return 0;
    }

    @Override
    public Either<String, List<Review>> reviewsOrderByDateAsc(int id) {
        return null;
    }

    @Override
    public Either<String, List<Review>> reviewsOrderByDateDesc(int id) {
        return null;
    }

    @Override
    public Either<String, List<Review>> reviewsOrderByRaitingAsc(int id) {
        return null;
    }

    @Override
    public Either<String, List<Review>> reviewsOrderByRaitingDesc(int id) {
        return null;
    }

    @Override
    public Either<String, List<Review>> reviewsByRaiting(int raiting) {
        return null;
    }
}
