package dao.springJDBC;

import dao.interfaces.DAOReviews;
import dao.DBConPool;

import dao.springJDBC.mappers.ReviewsMapper;
import model.Review;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import utils.Querys;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpringDAOReviews implements DAOReviews {
    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List<Review> getReviewByItem(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());


        return jdbcTemplate.query(Querys.SELECT_REVIEW_BY_ITEM_QUERY,new ReviewsMapper(),id);
    }

    @Override
    public List<Review> getReviewByItemByUser(int idUser, int idItem) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.query(Querys.SELECT_REVIEW_BY_ITEM_BY_USER_QUERY,new ReviewsMapper(),idItem,idUser);
    }


    @Override
    public List<Review> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());


        return jdbcTemplate.query(Querys.SELECT_REVIEW_QUERY,new ReviewsMapper());
    }

    @Override
    public List<Review> getAllByUser(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        return jdbcTemplate.query(Querys.SELECT_REVIEW_BY_CUSTOMER_QUERY,new ReviewsMapper(),id);
    }

    @Override
    public boolean save(Review review) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        java.util.Date date = Date.from(review.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        boolean confirmacion = false;
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update( con -> {
                PreparedStatement preparedStatement = con
                        .prepareStatement(Querys.INSERT_REVIEW_QUERY,
                                Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1,review.getRating());
                preparedStatement.setString(2,review.getTitle());
                preparedStatement.setString(3,review.getDescription());
                preparedStatement.setDate(4,new java.sql.Date(date.getTime()));
                preparedStatement.setInt(5,review.getPurchase().getIdPurchase());
                return preparedStatement;
            },keyHolder);

            review.setIdReview(keyHolder.getKey().intValue());


            confirmacion = true;
        }catch (EmptyResultDataAccessException e){
            Logger.getLogger(SpringDAOReviews.class.getName()).log(Level.SEVERE,null,e);
        }


        return confirmacion;
    }

    @Override
    public int update(Review review) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        java.util.Date date = Date.from(review.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return jdbcTemplate.update(Querys.UPDATE_REVIEW_QUERY,review.getRating(),review.getTitle(),review.getDescription(),new java.sql.Date(date.getTime()),review.getIdReview());

    }

    @Override
    public int delete(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        int res;
        try{
            res = jdbcTemplate.update(Querys.DELETE_REVIEW,id);
        }catch (DataIntegrityViolationException e){
            res = -1;
        }

        return res;

    }

    @Override
    public List<Review> getReviewByCustomer(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jdbcTemplate.query(Querys.SELECT_REVIEW_BY_CUSTOMER_QUERY,new ReviewsMapper(),id);

    }


}
