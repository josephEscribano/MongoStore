/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

import dao.DAOFactory;
import model.Review;
public class ReviewsServices {


    private DAOFactory dao;

    public ReviewsServices() {
        this.dao = new DAOFactory();
    }


    public int updateReview(Review review){
        return dao.getDAOReviews().update(review);
    }
    public List<Review> getALLReviewsByUser(int id){
        return dao.getDAOReviews().getAllByUser(id);
    }

    public List<Review> getReviewsByItemByUser(int idUser,int idItem){
        return dao.getDAOReviews().getReviewByItemByUser(idUser,idItem);
    }
    public List<Review> getAllReviews() {
        return dao.getDAOReviews().getAll();
    }

    public int deleteReview( Review review) {
        return dao.getDAOReviews().delete(review);
    }

    public List<Review> searchByItem(int id) {
        return dao.getDAOReviews().getReviewByItem(id);
    }


    public boolean addReview(Review review) {
        return dao.getDAOReviews().save(review);
    }

    public List<Review> searchByCustomer(int id) {
        return dao.getDAOReviews().getReviewByCustomer(id);
    }
}
