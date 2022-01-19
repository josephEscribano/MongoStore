package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name  = "purchaseByItem",
        query = "select count(p.idPurchase) from Purchase p where p.itemsByIdItem.id = :id"
        ),
        @NamedQuery(name = "purchaseByReview",
        query = "select count (*) from Purchase p join Review r on p.idPurchase = r.purchasesByIdPurchase.idPurchase where r.purchasesByIdPurchase.idPurchase = :id"
        ),
        @NamedQuery(name = "getPurchase",
        query = "from Purchase"
        ),
        @NamedQuery(name = "getPurchasesByUser",
        query = "from Purchase where customersByIdCustomer.id = :id"
        ),
        @NamedQuery(name = "countPurhcaseByCustomer",
        query = "select count(p.customersByIdCustomer.id) from Purchase p where p.customersByIdCustomer.id = :id"),
        @NamedQuery(name = "purchaseByDate",
        query = "from Purchase where date = :date"),
        @NamedQuery(name = "purchaseBetweenDates",
        query = "from Purchase where date between :initialDate and :finalDate"
        ),
        @NamedQuery(name = "getPurchasesByItem",
                query = "from Purchase where itemsByIdItem.idItem = :id"
        )

})
@Entity
@Table(name = "Purchases")
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

    public Purchase( Customer customerByIdCustomer, Item itemByIdItem,LocalDate date) {
        this.date = date;
        this.customerByIdCustomer = customerByIdCustomer;
        this.itemByIdItem = itemByIdItem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPurchase", nullable = false)
    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
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
        Purchase purchase = (Purchase) o;
        return idPurchase == purchase.idPurchase;
    }

    @Override
    public String toString() {
        return "ID: " + idPurchase + "  Customer: " + customerByIdCustomer.getName() + "  Item: " + itemByIdItem.getName() + "  Date: " + date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPurchase);
    }

    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer", nullable = false)
    public Customer getCustomersByIdCustomer() {
        return customerByIdCustomer;
    }

    public void setCustomersByIdCustomer(Customer customerByIdCustomer) {
        this.customerByIdCustomer = customerByIdCustomer;
    }

    @ManyToOne
    @JoinColumn(name = "idItem", referencedColumnName = "idItem", nullable = false)
    public Item getItemsByIdItem() {
        return itemByIdItem;
    }

    public void setItemsByIdItem(Item itemByIdItem) {
        this.itemByIdItem = itemByIdItem;
    }

    @OneToMany(mappedBy = "purchasesByIdPurchase")
    public List<Review> getReviewsByIdPurchase() {
        return reviewByIdPurchase;
    }

    public void setReviewsByIdPurchase(List<Review> reviewByIdPurchase) {
        this.reviewByIdPurchase = reviewByIdPurchase;
    }
}
