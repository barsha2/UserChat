package com.barsha.userchat.Service;

import com.barsha.userchat.DBModel.UserMessegeTable;
import com.barsha.userchat.Model.UserChatResponse;

public interface UserMessegeService {
    UserChatResponse                NewMessege          (UserMessegeTable userMessegeTable);
    UserChatResponse                GetAllMessege       ();
}
