package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
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

}
