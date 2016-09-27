<%-- 
    Document   : ResultsPage
    Created on : Sep 19, 2016, 4:25:28 PM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
        <link href="bookCss.css" rel="stylesheet" type="text/css"/>
        <title>Authors</title>
    </head>
    <body>
        <div class="container">
            <h1>Authors</h1>
            <center>
            <table class="table-striped table-hover table-condensed">
                <c:forEach var="item" items="${Authors}">
                    <tr>
                        <th>Name</th>
                        <th>Author ID</th>
                        <th>Date Added</th>
                    </tr>
                    <tr>
                        <td>${item.authorName}</td>
                        <td>${item.authorId}</td>
                        <td>${item.dateAdded}</td>

                    </tr>
                </c:forEach>
            </table>
            </center>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>
