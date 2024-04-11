package com.barsha.userchat.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barsha.userchat.Constants.ApplicationConstant;
import com.barsha.userchat.Constants.CommonFunctions;
import com.barsha.userchat.DBModel.UserMessegeTable;
import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Model.CommonResponse;
import com.barsha.userchat.Model.GetAllMessegeResponse;
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
    public CommonResponse NewMessege(UserMessegeTable userMessegeTable) {

        int     functionResult      = ApplicationConstant.ZERO;
        String  nextStep            = ApplicationConstant.NEXT_STEP_TO_CONTINUE;

        CommonResponse      commonResponse      = new CommonResponse();

        userMessegeTable.setMessegeDate(CommonFunctions.GetCurrentDate());
        userMessegeTable.setMessegeTime(CommonFunctions.GetCurrentTime());

        functionResult  = userMessegeTableDao.InsertUserMessegeTable(userMessegeTable);

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
    public List<GetAllMessegeResponse> GetAllMessege() {
        
        String          nextStep    = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
        String          userID      = ApplicationConstant.SPACES;

        List<UserMessegeTable>      userMessegeTableList        = new ArrayList<>();
        UserMessegeTable            userMessegeTable            = new UserMessegeTable();
        List<UserRegistrationTable> userRegistrationTableList   = new ArrayList<>();
        UserRegistrationTable       userRegistrationTable       = new UserRegistrationTable();
        GetAllMessegeResponse       getAllMessegeResponse       = new GetAllMessegeResponse();
        List<GetAllMessegeResponse> getAllMessegeResponseList   = new ArrayList<>();

        userMessegeTableList    = userMessegeTableDao.GetAllMessege();

        if (userMessegeTableList != null) {
            if (userMessegeTableList.size() > 0) {
                nextStep = ApplicationConstant.NEXT_STEP_TO_CONTINUE;
            }
        }
        else {
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
                    nextStep = ApplicationConstant.NEXT_STEP_TO_STOP;
                }

                getAllMessegeResponseList.add(getAllMessegeResponse);
            }
        }

        return getAllMessegeResponseList;
    }
    
}
