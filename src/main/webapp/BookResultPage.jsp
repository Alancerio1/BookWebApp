<%-- 
    Document   : BookResultPage
    Created on : Nov 2, 2016, 9:07:06 PM
    Author     : alancerio18
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Page</title>
    </head>
    <body>
    <center>
        <h1>Book Page</h1>

        <form name="listForm" method="POST" action="BookController">
            <input type="submit" value="Add" name="submit" />
            <input type ="submit" value="Update" name ="submit" />&nbsp;
            <input type ="submit" value="Delete" name ="submit" />

            <table border ="1" class="table-hover table-condensed">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>ISBN</th>
                    <th>Author</th>
                </tr>

                <c:forEach var="item" items="${books}">
                    <tr>
                        <td><input type="checkbox" name="bookId" value="${item.bookId}"></td> 
                        <td>${item.title}</td>
                        <td>${item.isbn}</td>
                        <td>${item.authorId.authorName}</td>

                    </tr>
                </c:forEach>

            </table>
        </form>

    </center>
</body>
</html>
