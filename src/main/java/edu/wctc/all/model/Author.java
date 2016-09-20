/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author alancerio18
 */
public class Author {
    int authorId;
    String authorName;
    Date dateAdded;

    public Author() {
    }

//    public Author(int authorId) {
//        this.authorId = authorId;
//    }

    public Author(int authorId, String authorName, Date dateAdded) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }

   
    
    

    public final int getAuthorId() {
        return authorId;
    }

    public final void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public final String getAuthorName() {
        return authorName;
    }

    public final void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public final Date getDateAdded() {
        return dateAdded;
    }

    public final void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.authorId;
        hash = 17 * hash + Objects.hashCode(this.authorName);
        hash = 17 * hash + Objects.hashCode(this.dateAdded);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "AuthorService{" + "authorId=" + authorId + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }
    
    
    
    
}
