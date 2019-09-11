<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 01/25/2018
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tela de Login</title>
</head>
<body>
<form action="login" method="post">
    <fieldset style="width: 200px; margin: 0 auto; padding: 40px">
        <legend>Login</legend>

        <label>Login:</label>
        <input type="text" name="login"/>
        <br/>
        <br/>
        <label>Senha:</label>
        <input type="password" name="senha"/>
        <br/>
        <br/>
        <input type="submit" value="Logar"/>
        <br/>
        <br/>
        <%
            String msg = (String) request.getAttribute("msg");
        %>
        <%= msg != null ? msg : ""%>
    </fieldset>
</form>
</body>
</html>