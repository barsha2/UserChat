package com.barsha.userchat.DBModel;

import java.math.BigInteger;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessegeTable {
    private BigInteger  uniqueID;
    private String      userID;
    private String      messege;
    private Date        messegeDate;
    private String      messegeTime;
}
