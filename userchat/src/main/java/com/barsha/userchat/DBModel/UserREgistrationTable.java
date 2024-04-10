package com.barsha.userchat.DBModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationTable {
    private String userID;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String city;
    private String state;
}
