package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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


}
