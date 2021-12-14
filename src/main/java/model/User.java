package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NamedQuery(name = "findUser", query = "select u from User u where name = :name and password = :pass")
@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int idUser;
    private String name;
    private String password;
    private Customer customerByIdUser;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }

    @OneToOne(mappedBy = "usersByIdCustomer")
    public Customer getCustomersByIdUser() {
        return customerByIdUser;
    }

    public void setCustomersByIdUser(Customer customerByIdUser) {
        this.customerByIdUser = customerByIdUser;
    }
}
