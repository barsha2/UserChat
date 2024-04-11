package com.barsha.userchat.Constants;

public class SQLQuery {
    public static final String INSERT_USER_REGISTRATION_TABLE               = "INSERT INTO tbl_user_registration (user_id, first_name, last_name, password, address, city, state) VALUES (:userid, :firstname, :lastname, :password, :address, :city, :state)";
    public static final String SELECT_USER_REGISTRATION_TABLE_BY_USERID     = "SELECT * FROM tbl_user_registration WHERE user_id = :userid";

    public static final String INSERT_USER_MESSEGE_TABLE                    = "INSERT INTO tbl_user_messege (user_id, messege, messege_date, messege_time) VALUE (:userid, :messege, :messegedate, :messegetime)";
    public static final String SELECT_USER_MESSEGE_TABLE                    = "SELECT * FROM tbl_user_messege ORDER BY messege_date ASC, messege_time ASC";
}
