/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Review;

import java.util.List;

/**
 *
 */
public interface DAOReviews {

    Review get(int id);

    List<Review> getReviewByItem (int id);
     
    List<Review> getAll();
     
    boolean save(Review t);
     
    Review update(Review t);
     
    void delete(Review t);
}
