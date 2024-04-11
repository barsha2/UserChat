package com.barsha.userchat.Service;

import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.UserLoginRequest;

public interface AuthenticationService {
    CommonResponse      UserSignup          (UserRegistrationTable userRegistrationTable);
    CommonResponse      UserLogin           (UserLoginRequest userLoginRequest);
}
