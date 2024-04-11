package com.barsha.userchat.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.barsha.userchat.Constants.ApplicationConstant;
import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.UserChatResponse;
import com.barsha.userchat.Model.UserLoginRequest;
import com.barsha.userchat.Service.AuthenticationService;

@Controller
@RequestMapping("/Authentication")
public class AuthenticationController {

    private final Logger logger = Logger.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/Register")
    public ResponseEntity<UserChatResponse>  UserSignup (
            @RequestBody(required = true) UserRegistrationTable userRegistrationTable) {
     logger.debug("*** UserSignup *** - START");

        UserChatResponse           userChatResponse         = new UserChatResponse();
        HttpStatus                 httpStatus               = ApplicationConstant.HTTP_STATUS_ERROR;

        userChatResponse      = authenticationService.UserSignup(userRegistrationTable);
        
       if (((CommonResponse) userChatResponse.getApiResponse()).getTransactionResult().equals(ApplicationConstant.TRANSACTION_RESULT_SUCCESS)) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }

       logger.debug("*** UserSignup *** - END");
       return new ResponseEntity<> (userChatResponse, httpStatus);
    }

    @PostMapping("/Login")
    public ResponseEntity<UserChatResponse>  UserLogin (
            @RequestBody(required = true) UserLoginRequest userLoginRequest) {
     logger.debug("*** UserLogin *** - START");
        UserChatResponse           userChatResponse         = new UserChatResponse();
        CommonResponse             commonResponse           = new CommonResponse();
        HttpStatus                 httpStatus               = ApplicationConstant.HTTP_STATUS_ERROR;

        userChatResponse      = authenticationService.UserLogin(userLoginRequest);
        
        if (((CommonResponse) userChatResponse.getApiResponse()).getTransactionResult().equals(ApplicationConstant.TRANSACTION_RESULT_SUCCESS)) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
        }
        else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
        }
       logger.debug("*** UserLogin *** - END");
        return new ResponseEntity<> (userChatResponse, httpStatus);
    }
}
