<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 02/21/2018
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="test" method="post">
    <br/>
    <input type="submit" value="test"/>
    <br/>

    <%
        String msg = (String) request.getAttribute("msg");
    %>
    <%= msg != null ? msg : ""%>
</form>
</body>
</html>
