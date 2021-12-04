package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int idCustomer;
    private String name;
    private String telephone;
    private String address;
    private User userByIdCustomer;
    private List<Purchase> purchaseByIdCustomer;

    public Customer(int idCustomer, String name, String telephone, String address) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.telephone = telephone;
        this.address = address;

    }

    public Customer(String name, String telephone, String address) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
    }

    @Id
    @Column(name = "idCustomer", nullable = false)
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "telephone", nullable = false, length = 100)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return idCustomer == customer.idCustomer;
    }

    @Override
    public String toString() {
        return "ID: " + idCustomer + "  Name: " + name + "  Phone: " + telephone + "  Address: " + address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer);
    }

    @OneToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idUser", nullable = false)
    public User getUsersByIdCustomer() {
        return userByIdCustomer;
    }

    public void setUsersByIdCustomer(User userByIdCustomer) {
        this.userByIdCustomer = userByIdCustomer;
    }

    @OneToMany(mappedBy = "customersByIdCustomer")
    public List<Purchase> getPurchasesByIdCustomer() {
        return purchaseByIdCustomer;
    }

    public void setPurchasesByIdCustomer(List<Purchase> purchaseByIdCustomer) {
        this.purchaseByIdCustomer = purchaseByIdCustomer;
    }
}
