package com.barsha.userchat.Service;

import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.UserChatResponse;
import com.barsha.userchat.Model.UserLoginRequest;

public interface AuthenticationService {
    UserChatResponse        UserSignup          (UserRegistrationTable userRegistrationTable);
    UserChatResponse        UserLogin           (UserLoginRequest userLoginRequest);
}
