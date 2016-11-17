<%-- 
    Document   : BookUpdate
    Created on : Nov 7, 2016, 7:08:06 PM
    Author     : alancerio18
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book update Page</title>
    </head>
    <body>
        <h1>Book Update Page</h1>
        
        <form method="Post" action="BookController">

            <input type ="submit" value="Cancel" name ="submit" />&nbsp;
            <input type ="submit" value="Updated" name ="submit" />&nbsp;
            
                 
            <c:forEach var="item" items="${books}">
                Book Id:<input type ="text" value="${item.bookId}" name="bookId" id="bookId" readonly />
                Book Title:<input type ="text" value="${item.title}" name="bookAdded" id="Add" />
                ISBN:<input type ="text" value="${item.isbn}" name="isbn" id="bookIsbn" readonly/>
            </c:forEach>
        </form>


           

          
    </body>
</html>
