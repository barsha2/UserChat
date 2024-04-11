package com.barsha.userchat.Constants;

import org.springframework.http.HttpStatus;

public class ApplicationConstant {
    
    public static final HttpStatus HTTP_STATUS_OK                       = HttpStatus.OK;                            //200
    public static final HttpStatus HTTP_STATUS_ACCEPTED                 = HttpStatus.ACCEPTED;                      //202
    public static final HttpStatus HTTP_STATUS_ERROR                    = HttpStatus.BAD_REQUEST;                   //400
    public static final HttpStatus HTTP_STATUS_UNAUTHORIZED             = HttpStatus.UNAUTHORIZED;                  //401
    public static final HttpStatus HTTP_STATUS_INTERNAL_SERVER_ERROR    = HttpStatus.INTERNAL_SERVER_ERROR;         //500

    public static final String      TRANSACTION_RESULT_SUCCESS          = "Success";
    public static final String      TRANSACTION_RESULT_FAILURE          = "Failure";

    public static final int         ZERO                                = 0;
    public static final int         ONE                                 = 1;

    public static final int INSERT_SUCCESSFUL                           = 0;    //INSERT
    public static final int INSERT_UNSUCCESSFUL                         = 1;
    public static final int INSERT_MULTIPLE_RECORDS                     = 2;
    public static final int INSERT_DATA_ACCESS_ERROR                    = 3;

    public static final String      NEXT_STEP_TO_CONTINUE               = "Continue";
    public static final String      NEXT_STEP_TO_STOP                   = "Stop";

    public static final String      SPACES                              = "";
}
