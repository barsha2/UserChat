package com.barsha.userchat.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.barsha.userchat.DBModel.UserRegistrationTable;

public class UserRegistrationTableMapper implements RowMapper<UserRegistrationTable>{

    @Override
    public UserRegistrationTable mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRegistrationTable       userRegistrationTable       = new UserRegistrationTable();

        userRegistrationTable.setUserID     (rs.getString("user_id"));
        userRegistrationTable.setFirstName  (rs.getString("first_name"));
        userRegistrationTable.setLastName   (rs.getString("last_name"));
        userRegistrationTable.setAddress    (rs.getString("address"));
        userRegistrationTable.setPassword   (rs.getString("password"));
        userRegistrationTable.setCity       (rs.getString("city"));
        userRegistrationTable.setState      (rs.getString("state"));

        return userRegistrationTable;
    }
    
}
