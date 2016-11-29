<%-- 
    Document   : BookAdd
    Created on : Nov 7, 2016, 7:07:45 PM
    Author     : alancerio18
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book add Page</title>
    </head>
    <body>
        <h1>Book Add Page</h1>


        <form method="Post" action="BookController">
            <input type ="submit" value="Cancel" name ="submit" />&nbsp;
            <input type ="submit" value="Create" name ="submit" />




            Book Title:<input type ="text" value="" name="bookAdd" id="Add" />
            Book ISBN:<input type ="text" value="" name="bookIsbn" id="IsbnAdd" />
            <c:choose>
                <c:when test="${authors == null}">
                    <h1>null</h1>
                </c:when>
                <c:when test="${authors != null}">
                    Book Author:

            <select name="bookAuthor">
                <c:forEach var="author" items="${authors}">

                    <option value="${author.authorId}" id="author">${author.authorName} </option>
                </c:forEach>
            </select>
                </c:when>
            </c:choose>
            

        </form>

    </body>
</html>
