package com.barsha.userchat.Repository.dao;

import java.util.List;

import com.barsha.userchat.DBModel.UserRegistrationTable;

public interface UserRegistrationTableDao {
    int                             InsertUserRegistrationTable     (UserRegistrationTable userRegistrationTable);
    List<UserRegistrationTable>     GetUserRegistrationTable        (String userID);
}
