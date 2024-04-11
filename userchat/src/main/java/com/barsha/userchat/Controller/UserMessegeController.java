package com.barsha.userchat.Controller;

import java.util.ArrayList;
import java.util.List;

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
import com.barsha.userchat.Model.GetAllMessegeResponse;
import com.barsha.userchat.Service.UserMessegeService;

@Controller
@RequestMapping("/UserMessege")
public class UserMessegeController {

     private final Logger logger = Logger.getLogger(UserMessegeController.class);

    @Autowired
    UserMessegeService userMessegeService;

    @PostMapping("/New")
    public ResponseEntity<CommonResponse>  NewMessege (
            @RequestBody(required = true) UserMessegeTable userMessegeTable) {

        logger.debug("*** NewMessege *** - START");
        CommonResponse          commonResponse          = new CommonResponse();
        HttpStatus              httpStatus              = ApplicationConstant.HTTP_STATUS_ERROR;

        commonResponse          = userMessegeService.NewMessege(userMessegeTable);
        
       if (commonResponse.getTransactionResult().equals(ApplicationConstant.TRANSACTION_RESULT_SUCCESS)) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }
       logger.debug("*** NewMessege *** - END");
        return new ResponseEntity<> (commonResponse, httpStatus);
    }

    @GetMapping("/GetAllMessege")
    public ResponseEntity<List<GetAllMessegeResponse>>  GetAllMessege () {

     logger.debug("*** GetAllMessege *** - START");
        List<GetAllMessegeResponse>     getAllMessegeResponseList   = new ArrayList<>();
        HttpStatus                      httpStatus                  = ApplicationConstant.HTTP_STATUS_ERROR;

        getAllMessegeResponseList          = userMessegeService.GetAllMessege();
        
       if (getAllMessegeResponseList != null) {
            httpStatus  = ApplicationConstant.HTTP_STATUS_OK;
       }
       else {
            httpStatus  = ApplicationConstant.HTTP_STATUS_ERROR;
       }
       logger.debug("*** GetAllMessege *** - END");
        return new ResponseEntity<> (getAllMessegeResponseList, httpStatus);
    }
}
