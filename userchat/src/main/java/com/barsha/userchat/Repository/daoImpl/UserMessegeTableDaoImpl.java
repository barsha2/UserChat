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
import com.barsha.userchat.DBModel.UserMessegeTable;
import com.barsha.userchat.Mapper.UserMessegeTableMapper;
import com.barsha.userchat.Repository.dao.UserMessegeTableDao;

@Repository
public class UserMessegeTableDaoImpl implements UserMessegeTableDao{

    private final Logger logger = Logger.getLogger(UserMessegeTableDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate template;
    
    @Override
    public int InsertUserMessegeTable(UserMessegeTable userMessegeTable) {
        logger.debug("*** InsertUserMessegeTable *** - START");
        int                     functionResult          = ApplicationConstant.ZERO;
        int                     rowsImpacted            = ApplicationConstant.ZERO;
        String                  sqlStatement            = SQLQuery.INSERT_USER_MESSEGE_TABLE;
        MapSqlParameterSource   mapSqlParameterSource   = new MapSqlParameterSource();
      
        mapSqlParameterSource.addValue("userid",                userMessegeTable.getUserID());
        mapSqlParameterSource.addValue("messege",               userMessegeTable.getMessege());
        mapSqlParameterSource.addValue("messegedate",           userMessegeTable.getMessegeDate());
        mapSqlParameterSource.addValue("messegetime",           userMessegeTable.getMessegeTime());

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
        logger.debug("*** InsertUserMessegeTable *** - START");
        return functionResult;
    }

    @Override
    public List<UserMessegeTable> GetAllMessege() {
        logger.debug("*** GetAllMessege *** - START");
        String  sqlStatement    = ApplicationConstant.SPACES;

        List<UserMessegeTable>          userMessegeTableList        = new ArrayList<>();
        MapSqlParameterSource           parameterSource             = new MapSqlParameterSource();

        sqlStatement = SQLQuery.SELECT_USER_MESSEGE_TABLE;

        try {
            userMessegeTableList = template.query(sqlStatement, parameterSource, new UserMessegeTableMapper());
        }
        catch (DataAccessException e) {
            logger.error(e);
            return null;
        }
        logger.debug("*** GetAllMessege *** - END");
        return userMessegeTableList;
    }
    
}
