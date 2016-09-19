/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alancerio18
 */
public class AuthorService {

    private List<Author> authors;

    public AuthorService() {
        fakeDatabase();
    }

    private void fakeDatabase() {
        authors = Arrays.asList(
                new Author(1, "John,Doe", new Date()),
                new Author(2, "Jane,Doe", new Date()),
                new Author(3, "Jack,Jack", new Date())
                );
    }
    public List<Author> getAuthorList(){
        return authors;
    }
}
