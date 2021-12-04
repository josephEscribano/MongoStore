package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Items")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int idItem;
    private String name;
    private String company;
    private double price;
    private List<Purchase> purchaseByIdItem;

    public Item(int idItem, String name, String company, double price) {
        this.idItem = idItem;
        this.name = name;
        this.company = company;
        this.price = price;
    }

    public Item(String name, String company, double price) {
        this.name = name;
        this.company = company;
        this.price = price;
    }

    @Id
    @Column(name = "idItem", nullable = false)
    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "company", nullable = false, length = 45)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return idItem == item.idItem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem);
    }

    @Override
    public String toString() {
        return idItem + " " + name + " " + price + "€";
    }

    @OneToMany(mappedBy = "itemsByIdItem")
    public List<Purchase> getPurchasesByIdItem() {
        return purchaseByIdItem;
    }

    public void setPurchasesByIdItem(List<Purchase> purchaseByIdItem) {
        this.purchaseByIdItem = purchaseByIdItem;
    }
}
