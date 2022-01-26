package dao.mongoDAO;

import dao.interfaces.DAOPurchases;
import io.vavr.control.Either;
import model.Purchase;

import java.time.LocalDate;
import java.util.List;

public class PurchaseDAO implements DAOPurchases {
    @Override
    public int getPurchasesByItemId(int id) {
        return 0;
    }

    @Override
    public int getPurchasesByReviewId(int id) {
        return 0;
    }

    @Override
    public List<Purchase> getAll() {
        return null;
    }

    @Override
    public List<Purchase> getAllPurchaseForUser(int id) {
        return null;
    }

    @Override
    public boolean save(Purchase t) {
        return false;
    }

    @Override
    public int update(Purchase t) {
        return 0;
    }

    @Override
    public int searchCustomerByid(int id) {
        return 0;
    }

    @Override
    public int delete(Purchase purchase) {
        return 0;
    }

    @Override
    public List<Purchase> findPurchaseByDate(LocalDate localDate) {
        return null;
    }

    @Override
    public List<Purchase> purchasesBetweenDates(LocalDate initialDate, LocalDate finalDate) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByItem(int id) {
        return null;
    }

    @Override
    public Either<String, Integer> numbersPurchasesByMonth(int mes, int year, int id) {
        return null;
    }
}
