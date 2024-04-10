package com.barsha.userchat.Service;

import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.CommonResponse;

public interface AuthenticationService {
    CommonResponse      UserSignup          (UserRegistrationTable userRegistrationTable);
}
