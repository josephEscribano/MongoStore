/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import io.vavr.control.Either;
import model.Review;

import java.util.List;

/**
 *
 */
public interface DAOReviews {

    Review get(int id);

    List<Review> getReviewByItem (int id);
    List<Review> getReviewByItemByUser(int idUser, int idItem);

    List<Review> getAll();

    List<Review> getAllByUser(int id);
     
    boolean save(Review t);
     
    int update(Review t);
     
    int delete(Review review);

    List<Review> getReviewByCustomer(int id);

    int checkItemReview(int id);

    Either<String, Integer> averageRaitingByItem(int id);

    double countReviewsByItem(int id);

    Either<String,List<Review>> reviewsOrderByDateAsc(int id);

    Either<String,List<Review>> reviewsOrderByDateDesc(int id);

    Either<String,List<Review>> reviewsOrderByRaitingAsc(int id);

    Either<String,List<Review>> reviewsOrderByRaitingDesc(int id);
}
