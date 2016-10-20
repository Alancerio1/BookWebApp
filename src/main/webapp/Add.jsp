<%-- 
    Document   : addEdit
    Created on : Oct 10, 2016, 12:24:25 AM
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
        <title>Add Page</title>
    </head>
    <body>
        <h1>Add Page</h1>
   
        <form method="Post" action="AuthorController">
            <input type ="submit" value="Cancel" name ="submit" />&nbsp;
            <input type ="submit" value="Create" name ="submit" />

                        

                
                Author Name:<input type ="text" value="" name="Add" id="Add" />
               
        </form>
    </body>
</html>
