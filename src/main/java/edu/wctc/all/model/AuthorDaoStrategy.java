/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alancerio18
 */
public interface AuthorDaoStrategy {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;

    void initDao(String driverClass, String Url, String Password, String userName);
    
    void updateAuthor(List<String> columnNames, List<Object> columnValues,Object whereValue ) throws Exception;
    
    void deleteAuthorById(String id) throws Exception;
    
    void createRecord(List<Object> columnValues) throws Exception;
    
    Author findAuthorById(Integer authorId)throws Exception;

}
