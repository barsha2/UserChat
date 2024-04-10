package com.barsha.userchat.Controller;

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
import com.barsha.userchat.Service.AuthenticationService;

@Controller
@RequestMapping("/Registration")
public class AuthenticationController {
    
    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/User")
    public ResponseEntity<CommonResponse>  UserSignup (
            @RequestBody(required = true) UserRegistrationTable userRegistrationTable) {

        CommonResponse          commonResponse          = new CommonResponse();
        HttpStatus              httpStatus              = ApplicationConstant.HTTP_STATUS_ERROR;

        commonResponse      = authenticationService.UserSignup(userRegistrationTable);
        
       if (commonResponse.getTransactionResult().equals(ApplicationConstant.TRANSACTION_RESULT_SUCCESS)) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }

        return new ResponseEntity<> (commonResponse, httpStatus);
    }
}
