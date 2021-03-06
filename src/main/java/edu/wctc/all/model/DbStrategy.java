/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author alancerio18
 */
public interface DbStrategy {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;

    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;

    void deleteById(String author, String author_id, Object primaryKeyValue) throws Exception;

    void createRecord(String tableName, List<String> columnNames, List<Object> columnValues) throws Exception;
  
    //void createRecord(String name) throws Exception;


    Map<String, Object> findById(String tableName, String columnName, Object primaryKey) throws SQLException;

    void updateRecord(String tableName, List<String> columnNames, List<Object> columnValues,
            String whereColumn, Object whereValue) throws Exception;

    void openConnection(DataSource ds) throws SQLException;

}
