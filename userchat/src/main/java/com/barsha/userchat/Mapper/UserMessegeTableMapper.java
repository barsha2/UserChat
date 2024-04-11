package com.barsha.userchat.Mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.barsha.userchat.DBModel.UserMessegeTable;

public class UserMessegeTableMapper implements RowMapper<UserMessegeTable>{

    @Override
    public UserMessegeTable mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        UserMessegeTable    userMessegeTable    = new UserMessegeTable();

        userMessegeTable.setUniqueID        (BigInteger.valueOf(rs.getLong("unique_id")));
        userMessegeTable.setUserID          (rs.getString("user_id"));
        userMessegeTable.setMessege         (rs.getString("messege"));
        userMessegeTable.setMessegeDate     (rs.getDate("messege_date"));
        userMessegeTable.setMessegeTime     (rs.getString("messege_time"));

        return userMessegeTable;
    }
    
}
