package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
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
}
