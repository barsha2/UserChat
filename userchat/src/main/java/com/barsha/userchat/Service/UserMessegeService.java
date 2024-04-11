package com.barsha.userchat.Service;


import java.util.List;

import com.barsha.userchat.DBModel.UserMessegeTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.GetAllMessegeResponse;

public interface UserMessegeService {
    CommonResponse                  NewMessege          (UserMessegeTable userMessegeTable);
    List<GetAllMessegeResponse>     GetAllMessege       ();
}
