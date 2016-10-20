/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author alancerio18
 */
@SessionScoped
public class AuthorService implements Serializable {

    private Connection conn;
    @Inject
    private AuthorDaoStrategy dao;

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }

    public AuthorService() {
    }

    public void createRecord(String authorName) throws Exception{
        List<Object> columnValues = new ArrayList<>();
        columnValues.add(authorName);
        columnValues.add(new Date());
        dao.createRecord(columnValues);
    }
    
    public void deleteById(String id) throws Exception {
        dao.deleteAuthorById(id);
    }
     public Author getAuthorById(String authorId) throws Exception {
        return dao.findAuthorById(Integer.parseInt(authorId));
    }

    public void updateAuthor(String authorId,String authorName) throws Exception {
        List<String> columnName = Arrays.asList("author_name");
        List<Object> columnValue = new ArrayList();
        columnValue.add(authorName);
        dao.updateAuthor(columnName,columnValue,authorId);
    }

    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        return dao.getAuthorList();
    }

//    public static void main(String[] args) throws Exception{
////        AuthorDaoStrategy dao = new AuthorDao(new MySqlDbStrategy(),"com.mysql.jdbc.Driver",
////                                      "jdbc:mysql://localhost:3306/book?useSSL=false","root","admin");
////        AuthorService service = new AuthorService(dao);
//        List<Author> authors = service.getAuthorList();
//        System.out.println(authors);
//    }
}
