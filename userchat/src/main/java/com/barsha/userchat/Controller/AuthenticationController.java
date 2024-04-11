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
import com.barsha.userchat.Model.UserLoginRequest;
import com.barsha.userchat.Service.AuthenticationService;

@Controller
@RequestMapping("/Authentication")
public class AuthenticationController {

    private final Logger logger = Logger.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/Register")
    public ResponseEntity<CommonResponse>  UserSignup (
            @RequestBody(required = true) UserRegistrationTable userRegistrationTable) {
     logger.debug("*** UserSignup *** - START");

        CommonResponse          commonResponse          = new CommonResponse();
        HttpStatus              httpStatus              = ApplicationConstant.HTTP_STATUS_ERROR;

        commonResponse      = authenticationService.UserSignup(userRegistrationTable);
        
       if (commonResponse.getTransactionResult().equals(ApplicationConstant.TRANSACTION_RESULT_SUCCESS)) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }

       logger.debug("*** UserSignup *** - END");
       return new ResponseEntity<> (commonResponse, httpStatus);
    }

    @PostMapping("/Login")
    public ResponseEntity<CommonResponse>  UserLogin (
            @RequestBody(required = true) UserLoginRequest userLoginRequest) {
     logger.debug("*** UserLogin *** - START");
        CommonResponse          commonResponse          = new CommonResponse();
        HttpStatus              httpStatus              = ApplicationConstant.HTTP_STATUS_ERROR;

        commonResponse      = authenticationService.UserLogin(userLoginRequest);
        
       if (commonResponse.getTransactionResult().equals(ApplicationConstant.TRANSACTION_RESULT_SUCCESS)) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }
       logger.debug("*** UserLogin *** - END");
        return new ResponseEntity<> (commonResponse, httpStatus);
    }
}
