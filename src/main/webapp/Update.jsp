<%-- 
    Document   : Update
    Created on : Oct 12, 2016, 6:10:26 PM
    Author     : alancerio18
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <h1>Update Page</h1>
        <form method="Post" action="AuthorController">

            <input type ="submit" value="Cancel" name ="submit" />&nbsp;
            <input type ="submit" value="Updated" name ="submit" />&nbsp;

            <c:forEach var="item" items="${authors}">

                Author Id<input type ="text" value="${item.authorId}" name="id" id="authorId" readonly />
                Author Name:<input type ="text" value="${item.authorName}" name="Added" id="Add" />
                Date Added:<input type ="text" value="${item.dateAdded}" name="date" id="dateAdded" readonly/>

            </c:forEach>
        </form>

    </body>
</html>
