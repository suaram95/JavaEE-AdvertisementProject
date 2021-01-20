package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String username;
    private String password;
    private UserType userType;
    private String pictureUrl;
}
