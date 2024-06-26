package com.barsha.userchat.Repository.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.barsha.userchat.Constants.ApplicationConstant;
import com.barsha.userchat.Constants.SQLQuery;
import com.barsha.userchat.DBModel.UserRegistrationTable;
import com.barsha.userchat.Mapper.UserRegistrationTableMapper;
import com.barsha.userchat.Repository.dao.UserRegistrationTableDao;

@Repository
public class UserRegistrationDaoImpl implements UserRegistrationTableDao{

    private final Logger logger = Logger.getLogger(UserRegistrationDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public int InsertUserRegistrationTable(UserRegistrationTable userRegistrationTable) {
        logger.debug("*** InsertUserRegistrationTable *** - START");
        int                     functionResult          = ApplicationConstant.ZERO;
        int                     rowsImpacted            = ApplicationConstant.ZERO;
        String                  sqlStatement            = SQLQuery.INSERT_USER_REGISTRATION_TABLE;
        MapSqlParameterSource   mapSqlParameterSource   = new MapSqlParameterSource();
      
        mapSqlParameterSource.addValue("userid",                userRegistrationTable.getUserID());
        mapSqlParameterSource.addValue("firstname",             userRegistrationTable.getFirstName());
        mapSqlParameterSource.addValue("lastname",              userRegistrationTable.getLastName());
        mapSqlParameterSource.addValue("password",              userRegistrationTable.getPassword());
        mapSqlParameterSource.addValue("address",               userRegistrationTable.getAddress());
        mapSqlParameterSource.addValue("city",                  userRegistrationTable.getCity());
        mapSqlParameterSource.addValue("state",                 userRegistrationTable.getState());

        try {
            rowsImpacted = template.update(sqlStatement, mapSqlParameterSource);
            if (rowsImpacted == ApplicationConstant.ZERO) {
                functionResult = ApplicationConstant.INSERT_UNSUCCESSFUL;
            }
            else {
                if (rowsImpacted == ApplicationConstant.ONE) {
                    functionResult = ApplicationConstant.INSERT_SUCCESSFUL;
                }
                else {
                    functionResult = ApplicationConstant.INSERT_MULTIPLE_RECORDS;
                }
            }
        }
        catch (DataAccessException e) {
            logger.error(e);
            functionResult = ApplicationConstant.INSERT_DATA_ACCESS_ERROR;
        }
        logger.debug("*** InsertUserRegistrationTable *** - START");
        return functionResult;
    }

    @Override
    public List<UserRegistrationTable> GetUserRegistrationTable(String userID) {
        logger.debug("*** GetUserRegistrationTable *** - START");
        String  sqlStatement    = ApplicationConstant.SPACES;

        List<UserRegistrationTable>     userRegistrationTableList   = new ArrayList<>();
        MapSqlParameterSource           parameterSource             = new MapSqlParameterSource();

        sqlStatement = SQLQuery.SELECT_USER_REGISTRATION_TABLE_BY_USERID;
        parameterSource.addValue("userid", userID);

        try {
            userRegistrationTableList = template.query(sqlStatement, parameterSource, new UserRegistrationTableMapper());
        }
        catch (DataAccessException e) {
            return null;
        }
        logger.debug("*** GetUserRegistrationTable *** - END");
        return userRegistrationTableList;
    }
    
}
