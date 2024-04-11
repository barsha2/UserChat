package com.barsha.userchat.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barsha.userchat.Constants.ApplicationConstant;
import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.UserLoginRequest;
import com.barsha.userchat.Repository.dao.UserRegistrationTableDao;
import com.barsha.userchat.Service.AuthenticationService;

@Service
public class AuthenticationSeviceImpl implements AuthenticationService{

    @Autowired
    UserRegistrationTableDao userRegistrationTableDao;

    @Override
    public CommonResponse UserSignup(UserRegistrationTable userRegistrationTable) {
        
        int     functionResult      = ApplicationConstant.ZERO;
        String  nextStep            = ApplicationConstant.NEXT_STEP_TO_CONTINUE;

        CommonResponse      commonResponse      = new CommonResponse();

        functionResult  = userRegistrationTableDao.InsertUserRegistrationTable(userRegistrationTable);

        switch (functionResult) {
            case ApplicationConstant.INSERT_SUCCESSFUL :
                nextStep        = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
            break;
            case ApplicationConstant.INSERT_UNSUCCESSFUL :
                nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                  
            break;
            case ApplicationConstant.INSERT_MULTIPLE_RECORDS :
                nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                
            break;
            case ApplicationConstant.INSERT_DATA_ACCESS_ERROR :
                nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                
            break;
        }

        if (nextStep.equals(ApplicationConstant.NEXT_STEP_TO_CONTINUE)) {
            commonResponse.setTransactionResult(ApplicationConstant.TRANSACTION_RESULT_SUCCESS);
        }
        else {
            commonResponse.setTransactionResult(ApplicationConstant.NEXT_STEP_TO_STOP);
        }

        return commonResponse;
    }

    @Override
    public CommonResponse UserLogin(UserLoginRequest userLoginRequest) {
        
        String      userID      = ApplicationConstant.SPACES;
        String      password    = ApplicationConstant.SPACES;
        String      nextStep    = ApplicationConstant.NEXT_STEP_TO_CONTINUE;

        UserRegistrationTable       userRegistrationTable           = new UserRegistrationTable();
        List<UserRegistrationTable> userRegistrationTableList       = new ArrayList<>();
        CommonResponse              commonResponse                  = new CommonResponse();

        userID      = userLoginRequest.getUserID();
        password    = userLoginRequest.getPassword();

        userRegistrationTableList   = userRegistrationTableDao.GetUserRegistrationTable(userID);

        if (userRegistrationTableList != null) {
            if (userRegistrationTableList.size() > 0) {
                nextStep = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
            }
        }
        else {
            nextStep = ApplicationConstant.NEXT_STEP_TO_STOP;
        }

        if (nextStep.equals(ApplicationConstant.NEXT_STEP_TO_CONTINUE)) {
            userRegistrationTable = userRegistrationTableList.get(0);

            if (userRegistrationTable.getPassword().equals(password)) {
                commonResponse.setTransactionResult(ApplicationConstant.TRANSACTION_RESULT_SUCCESS);
            }
            else {
                commonResponse.setTransactionResult(ApplicationConstant.TRANSACTION_RESULT_FAILURE);
            }
        }

        return commonResponse;
    }
    

}
