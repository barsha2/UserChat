package com.barsha.userchat.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barsha.userchat.Constants.ApplicationConstant;
import com.barsha.userchat.Constants.CommonFunctions;
import com.barsha.userchat.Constants.UserChatErrors;
import com.barsha.userchat.DBModel.UserMessegeTable;
import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.GetAllMessegeResponse;
import com.barsha.userchat.Model.UserChatResponse;
import com.barsha.userchat.Repository.dao.UserMessegeTableDao;
import com.barsha.userchat.Repository.dao.UserRegistrationTableDao;
import com.barsha.userchat.Service.UserMessegeService;

@Service
public class UserMessegeServiceImpl implements UserMessegeService{

    @Autowired
    UserMessegeTableDao userMessegeTableDao;

    @Autowired
    UserRegistrationTableDao userRegistrationTableDao;

    @Override
    public UserChatResponse NewMessege(UserMessegeTable userMessegeTable) {

        int     functionResult      = ApplicationConstant.ZERO;
        String  nextStep            = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
        String  errorCode           = ApplicationConstant.SPACES;

        CommonResponse      commonResponse      = new CommonResponse();
        UserChatResponse    userChatResponse    = new UserChatResponse();
        List<String>        errorList           = new ArrayList<>();

        userMessegeTable.setMessegeDate(CommonFunctions.GetCurrentDate());
        userMessegeTable.setMessegeTime(CommonFunctions.GetCurrentTime());

        functionResult  = userMessegeTableDao.InsertUserMessegeTable(userMessegeTable);

        switch (functionResult) {
            case ApplicationConstant.INSERT_SUCCESSFUL :
                nextStep        = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
            break;
            case ApplicationConstant.INSERT_UNSUCCESSFUL :
                errorCode       = UserChatErrors.INSERT_USER_MESSEGE_TABLE_NOT_SUCCESSFUL;
                errorList.add(errorCode);
                nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                  
            break;
            case ApplicationConstant.INSERT_MULTIPLE_RECORDS :
                errorCode       = UserChatErrors.INSERT_USER_MESSEGE_TABLE_INCORRECT;
                errorList.add(errorCode);
                nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                
            break;
            case ApplicationConstant.INSERT_DATA_ACCESS_ERROR :
                errorCode       = UserChatErrors.DATA_ACCESS_ERROR;
                errorList.add(errorCode);
                nextStep        = ApplicationConstant.NEXT_STEP_TO_STOP;
                
            break;
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
    public UserChatResponse GetAllMessege() {
        
        String          nextStep    = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
        String          userID      = ApplicationConstant.SPACES;
        String          errorCode   = ApplicationConstant.SPACES;

        List<UserMessegeTable>      userMessegeTableList        = new ArrayList<>();
        UserMessegeTable            userMessegeTable            = new UserMessegeTable();
        List<UserRegistrationTable> userRegistrationTableList   = new ArrayList<>();
        UserRegistrationTable       userRegistrationTable       = new UserRegistrationTable();
        GetAllMessegeResponse       getAllMessegeResponse       = new GetAllMessegeResponse();
        List<GetAllMessegeResponse> getAllMessegeResponseList   = new ArrayList<>();
        UserChatResponse            userChatResponse            = new UserChatResponse();
        List<String>                errorList                   = new ArrayList<>();

        userMessegeTableList    = userMessegeTableDao.GetAllMessege();

        if (userMessegeTableList != null) {
            if (userMessegeTableList.size() > 0) {
                nextStep = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
            }
        }
        else {
            errorCode = UserChatErrors.DATA_ACCESS_ERROR;
            errorList.add(errorCode);
            nextStep = ApplicationConstant.NEXT_STEP_TO_STOP;
        }

        if (nextStep.equals(ApplicationConstant.NEXT_STEP_TO_CONTINUE)) {
            for (int i = 0; i<userMessegeTableList.size(); i++) {
                userMessegeTable            = userMessegeTableList.get(i);
                userID                      = userMessegeTable.getUserID();
                getAllMessegeResponse   = new GetAllMessegeResponse();
                
                getAllMessegeResponse.setUserID(userID);
                getAllMessegeResponse.setMessege(userMessegeTable.getMessege());

                userRegistrationTableList   = userRegistrationTableDao.GetUserRegistrationTable(userID);

                if (userRegistrationTableList != null) {
                    if (userRegistrationTableList.size()>0) {
                        userRegistrationTable = userRegistrationTableList.get(0);

                        getAllMessegeResponse.setName(userRegistrationTable.getFirstName() + " " + userRegistrationTable.getLastName());
                    }
                }
                else {
                    errorCode = UserChatErrors.DATA_ACCESS_ERROR;
                    errorList.add(errorCode);
                    nextStep = ApplicationConstant.NEXT_STEP_TO_STOP;
                }

                getAllMessegeResponseList.add(getAllMessegeResponse);
            }
        }

        userChatResponse.setApiResponse(getAllMessegeResponseList);
        userChatResponse.setErrorList(errorList);

        return userChatResponse;
    }
    
}
