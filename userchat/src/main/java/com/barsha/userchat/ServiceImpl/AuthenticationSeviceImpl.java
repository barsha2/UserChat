package com.barsha.userchat.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barsha.userchat.Constants.ApplicationConstant;
import com.barsha.userchat.Constants.UserChatErrors;
import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.UserChatResponse;
import com.barsha.userchat.Model.UserLoginRequest;
import com.barsha.userchat.Repository.dao.UserRegistrationTableDao;
import com.barsha.userchat.Service.AuthenticationService;

@Service
public class AuthenticationSeviceImpl implements AuthenticationService{

    @Autowired
    UserRegistrationTableDao userRegistrationTableDao;

    @Override
    public UserChatResponse UserSignup(UserRegistrationTable userRegistrationTable) {
        
        int     functionResult      = ApplicationConstant.ZERO;
        String  nextStep            = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
        String  errorCode           = ApplicationConstant.SPACES;

        CommonResponse                  commonResponse                  = new CommonResponse();
        UserChatResponse                userChatResponse                = new UserChatResponse();
        List<String>                    errorList                       = new ArrayList<>();
        List<UserRegistrationTable>     userRegistrationTableList       = new ArrayList<>();


        userRegistrationTableList       = userRegistrationTableDao.GetUserRegistrationTable(userRegistrationTable.getUserID());

        if (userRegistrationTableList != null) {
            if (userRegistrationTableList.size() != 0) {
                nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                errorCode       = UserChatErrors.USER_ID_AVAILABLE;
                errorList.add(errorCode);
            } 
        }
        else {
            nextStep = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
        }

        if (nextStep.equals(ApplicationConstant.NEXT_STEP_TO_CONTINUE)) {
            functionResult  = userRegistrationTableDao.InsertUserRegistrationTable(userRegistrationTable);

            switch (functionResult) {
                case ApplicationConstant.INSERT_SUCCESSFUL :
                    nextStep        = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
                break;
                case ApplicationConstant.INSERT_UNSUCCESSFUL :
                    errorCode       = UserChatErrors.INSERT_USER_REGISTRATION_TABLE_NOT_SUCCESSFUL;
                    errorList.add(errorCode);
                    nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                    
                break;
                case ApplicationConstant.INSERT_MULTIPLE_RECORDS :
                    errorCode       = UserChatErrors.INSERT_USER_REGISTRATION_TABLE_INCORRECT;
                    errorList.add(errorCode);
                    nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                    
                break;
                case ApplicationConstant.INSERT_DATA_ACCESS_ERROR :
                    errorCode       = UserChatErrors.DATA_ACCESS_ERROR;
                    errorList.add(errorCode);
                    nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                    
                break;
            }
        }
        

        if (nextStep.equals(ApplicationConstant.NEXT_STEP_TO_CONTINUE)) {
            commonResponse.setTransactionResult(ApplicationConstant.TRANSACTION_RESULT_SUCCESS);
        }
        else {
            commonResponse.setTransactionResult(ApplicationConstant.NEXT_STEP_TO_STOP);
        }

        userChatResponse.setApiResponse(commonResponse);
        userChatResponse.setErrorList(errorList);
        return userChatResponse;
    }

    @Override
    public UserChatResponse UserLogin(UserLoginRequest userLoginRequest) {
        
        String      userID      = ApplicationConstant.SPACES;
        String      password    = ApplicationConstant.SPACES;
        String      nextStep    = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
        String      errorCode   = ApplicationConstant.SPACES;

        UserRegistrationTable       userRegistrationTable           = new UserRegistrationTable();
        List<UserRegistrationTable> userRegistrationTableList       = new ArrayList<>();
        CommonResponse              commonResponse                  = new CommonResponse();
        UserChatResponse            userChatResponse                = new UserChatResponse();
        List<String>                errorList                       = new ArrayList<>();

        userID      = userLoginRequest.getUserID();
        password    = userLoginRequest.getPassword();

        userRegistrationTableList   = userRegistrationTableDao.GetUserRegistrationTable(userID);

        if (userRegistrationTableList != null) {
            if (userRegistrationTableList.size() > 0) {
                nextStep = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
            }
        }
        else {
            errorCode   = UserChatErrors.USER_NOT_AVAILABLE;
            errorList.add(errorCode);
            nextStep    = ApplicationConstant.NEXT_STEP_TO_STOP;
        }

        if (nextStep.equals(ApplicationConstant.NEXT_STEP_TO_CONTINUE)) {
            userRegistrationTable = userRegistrationTableList.get(0);

            if (userRegistrationTable.getPassword().equals(password)) {
                commonResponse.setTransactionResult(ApplicationConstant.TRANSACTION_RESULT_SUCCESS);
            }
            else {
                commonResponse.setTransactionResult(ApplicationConstant.TRANSACTION_RESULT_FAILURE);
                errorCode   = UserChatErrors.PASSWORD_NOT_MATCHED;
                errorList.add(errorCode);
            }
        }

        userChatResponse.setErrorList(errorList);
        userChatResponse.setApiResponse(commonResponse);
        return userChatResponse;
    }
    

}
