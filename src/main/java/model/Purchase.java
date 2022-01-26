package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private int idPurchase;
    private LocalDate date;
    private Customer customerByIdCustomer;
    private Item itemByIdItem;
    private List<Review> reviewByIdPurchase;

    public Purchase(int idPurchase, Customer customerByIdCustomer, Item itemByIdItem, LocalDate date) {
        this.idPurchase = idPurchase;
        this.date = date;
        this.customerByIdCustomer = customerByIdCustomer;
        this.itemByIdItem = itemByIdItem;
    }

    public Purchase(Customer customerByIdCustomer, Item itemByIdItem, LocalDate date) {
        this.date = date;
        this.customerByIdCustomer = customerByIdCustomer;
        this.itemByIdItem = itemByIdItem;
    }

}
