/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.activation.DataSource;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author alancerio18
 */
@Dependent
public class AuthorDao implements AuthorDaoStrategy,Serializable {
    @Inject
    private DbStrategy db;
    private DataSource ds;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDao() {
      
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public  void deleteAuthorById(String id) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        Integer primaryKeyValue = Integer.parseInt(id);
        db.deleteById("author", "author_id", primaryKeyValue);
        db.closeConnection();
    }
    
    public  void createRecord(List<Object> columnValues) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        List<String> columnNames = Arrays.asList("author_name","date_added");
        db.createRecord("author" ,columnNames, columnValues);
        db.closeConnection();

    }
//    public  void createRecord(String name,List<String> columnNames, List<Object> columnValues) throws Exception {
//        db.openConnection(driverClass, url, userName, password);
//        db.createRecord("author_id", columnNames, columnValues);
//        db.closeConnection();
//
//    }
      public Author findAuthorById(Integer authorId)throws Exception{
       db.openConnection(driverClass, url, userName, password);
     
       Map<String,Object> data = db.findById("author", "author_id", authorId);
       Author author = new Author();
       author.setAuthorId((Integer)data.get("author_id"));
       author.setAuthorName(data.get("author_name").toString());
       author.setDateAdded((Date)data.get("date_added"));
       db.closeConnection();
       return author;
   }
      
    
    
    public  void initDao(String driverClass, String Url,String userName,String password){
        setDriverClass(driverClass);
        setUrl(Url);
        setUserName(userName);
        setPassword(password);
    }
    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        db.openConnection(driverClass, url, userName, password);
        List<Map<String, Object>> records = db.findAllRecords("author", 500);
        List<Author> authors = new ArrayList<>();
        for (Map<String, Object> rec : records) {
            Author author = new Author();
            int id = Integer.parseInt(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name").toString();
            author.setAuthorName(name != null ? name : "");
            Date date = (Date) rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }

        db.closeConnection();
        return authors;
    }
   

    public DbStrategy getDb() {
        return db;
    }

    public void setDb(DbStrategy db) {
        this.db = db;
    }

//    public static void main(String[] args) throws Exception {
//        AuthorDaoStrategy dao = new AuthorDao(new MySqlDbStrategy(), "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book?useSSL=false", "root", "admin");
//
//        List<Author> authors = dao.getAuthorList();
//        System.out.println(authors);
//
//    }

    @Override
    public void updateAuthor(List<String> columnNames, List<Object> columnValues,Object whereValue) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        db.updateRecord("author", columnNames, columnValues,"author_id", whereValue);
        db.closeConnection();
    }
}
