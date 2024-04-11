package com.barsha.userchat.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.barsha.userchat.Constants.ApplicationConstant;
import com.barsha.userchat.DBModel.UserMessegeTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.UserChatResponse;
import com.barsha.userchat.Service.UserMessegeService;

@Controller
@RequestMapping("/UserMessege")
public class UserMessegeController {

     private final Logger logger = Logger.getLogger(UserMessegeController.class);

    @Autowired
    UserMessegeService userMessegeService;

    @PostMapping("/New")
    public ResponseEntity<UserChatResponse>  NewMessege (
            @RequestBody(required = true) UserMessegeTable userMessegeTable) {

        logger.debug("*** NewMessege *** - START");
        UserChatResponse           userChatResponse         = new UserChatResponse();
        HttpStatus                 httpStatus              = ApplicationConstant.HTTP_STATUS_ERROR;

        userChatResponse          = userMessegeService.NewMessege(userMessegeTable);
        
       if (((CommonResponse) userChatResponse.getApiResponse()).getTransactionResult().equals(ApplicationConstant.TRANSACTION_RESULT_SUCCESS)) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }
       logger.debug("*** NewMessege *** - END");
        return new ResponseEntity<> (userChatResponse, httpStatus);
    }

    @GetMapping("/GetAllMessege")
    public ResponseEntity<UserChatResponse>  GetAllMessege () {

     logger.debug("*** GetAllMessege *** - START");
        UserChatResponse                userChatResponse              = new UserChatResponse();
        HttpStatus                      httpStatus                    = ApplicationConstant.HTTP_STATUS_ERROR;

        userChatResponse          = userMessegeService.GetAllMessege();
        
       if (userChatResponse.getApiResponse() != null) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }
       logger.debug("*** GetAllMessege *** - END");
        return new ResponseEntity<> (userChatResponse, httpStatus);
    }
}
