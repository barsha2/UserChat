package com.barsha.userchat.Constants;

public class SQLQuery {
    public static final String INSERT_USER_REGISTRATION_TABLE   = "INSERT INTO tbl_user_registration (user_id, first_name, last_name, password, address, city, state) VALUES (:userid, :firstname, :lastname, :password, :address, :city, :state)";
}
