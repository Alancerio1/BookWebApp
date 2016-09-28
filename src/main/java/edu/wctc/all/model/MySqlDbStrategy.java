/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author alancerio18
 */
public class MySqlDbStrategy implements DbStrategy {

    private Connection conn;

    @Override
    public void openConnection(String driverClass, String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {

        conn.close();
    }

    @Override
    public final void createRecord(String tableName, List<String> columnNames, List<Object> columnValues) throws Exception {
        String sql = "INSERT INTO " + tableName;
        StringJoiner sj = new StringJoiner(", ", " (", ")");
        for (String columnName : columnNames) {
            sj.add(columnName);
        }
        sql += sj.toString();
        sql += " VALUES ";
        sj = new StringJoiner(", ", " (", ")");
        for (Object columnValue : columnValues) {
            sj.add("?");
        }
        sql += sj.toString();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < columnValues.size(); i++) {
            ps.setObject(i + 1, columnValues.get(i));
        }
        ps.executeUpdate();
    }

    private PreparedStatement buildDeleteStatement(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws Exception {
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyFieldName + "=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, primaryKeyValue);
        return stmt;
    }

    @Override
    public final void deleteById(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws Exception {
        PreparedStatement stmt = buildDeleteStatement(tableName, primaryKeyFieldName, primaryKeyValue);
        stmt.executeUpdate();

    }
    //the delete statement that I made without a prepared statmenet
//    public void deleteRecord(String tableName, int primaryKey)
//            throws SQLException {
//
//        Statement deleteRecord = conn.createStatement();
//
//        String deleteString = "DELETE FROM " + tableName + " WHERE author_id = " + primaryKey;
//
//        deleteRecord.executeUpdate(deleteString);
//
//    }

    @Override
    public final List<Map<String, Object>> findById(String tableName, String columnName, Object primaryKey)
            throws SQLException {

        Statement findRecord = conn.createStatement();;
        String findString = "Select * FROM " + tableName + " WHERE" + columnName + "= " + primaryKey;
        // findRecord.executeUpdate(findString);

        ResultSet rs = findRecord.executeQuery(findString);

        List<Map<String, Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        while (rs.next()) {
            Map<String, Object> record = new LinkedHashMap<>();
            for (int i = 0; i < colCount; i++) {
                String colName = rsmd.getColumnName(i + 1);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);

        }
        return records;

    }

    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Map<String, Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        while (rs.next()) {
            Map<String, Object> record = new LinkedHashMap<>();
            for (int i = 0; i < colCount; i++) {
                String colName = rsmd.getColumnName(i + 1);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);

        }
        return records;
    }

    public static void main(String[] args) throws Exception {
        MySqlDbStrategy db = new MySqlDbStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book?useSSL=false",
                "root", "admin");
        //find = db.findById("author",1);
        //List<Map<String, Object>> records = db.findAllRecords("author", 500);
        List<String> colNames = Arrays.asList("author_name", "date_added");
        List<Object> colValues = new ArrayList<>();
        colValues.add("Jim Lombardo");
        colValues.add(new Date());
        db.createRecord("author", colNames, colValues);
        db.closeConnection();
    }

}
