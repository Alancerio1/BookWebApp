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
        <link href="bookCss.css" rel="stylesheet" type="text/css"/>
        <title>Authors</title>
    </head>
    <body>
        <h1>Authors</h1>

        <table>
            <c:forEach var="item" items="${authorList}">
                <tr>
                    <td>${item.authorName}</td>
                    <td>${item.authorId}</td>
                    <td>${item.dateAddded}</td>

                </tr>
            </c:forEach>
        </table>
    </body>
</html>
