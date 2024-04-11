package com.barsha.userchat.Repository.dao;

import java.util.List;

import com.barsha.userchat.DBModel.UserMessegeTable;

public interface UserMessegeTableDao {
    int                         InsertUserMessegeTable      (UserMessegeTable userMessegeTable);
    List<UserMessegeTable>      GetAllMessege               ();
}
