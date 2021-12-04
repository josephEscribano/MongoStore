package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private int idReview;
    private int rating;
    private String title;
    private String description;
    private LocalDate date;
    private Purchase purchaseByIdPurchase;

    public Review(int idReview, int rating, String title, String description, Purchase purchaseByIdPurchase) {
        this.idReview = idReview;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.purchaseByIdPurchase = purchaseByIdPurchase;
    }

    public Review(int rating, String title, String description, LocalDate date, Purchase purchaseByIdPurchase) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.date = date;
        this.purchaseByIdPurchase = purchaseByIdPurchase;
    }

    @Id
    @Column(name = "idReview", nullable = false)
    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return idReview == review.idReview;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReview);
    }

    @Override
    public String toString() {
        return "No. " + idReview + "  Item: " + purchaseByIdPurchase.getItemsByIdItem().getIdItem() + "\nRating: " + rating + "\nTitle: " + title + "\nComment: " + description + "\nDate: " + date + "  Customer: " + purchaseByIdPurchase.getCustomersByIdCustomer().getIdCustomer() + "  Purchase no. " + purchaseByIdPurchase.getIdPurchase();
    }

    @ManyToOne
    @JoinColumn(name = "idPurchase", referencedColumnName = "idPurchase", nullable = false)
    public Purchase getPurchasesByIdPurchase() {
        return purchaseByIdPurchase;
    }

    public void setPurchasesByIdPurchase(Purchase purchaseByIdPurchase) {
        this.purchaseByIdPurchase = purchaseByIdPurchase;
    }
}
