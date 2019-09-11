<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/28/2018
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login Page</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
    <c:import url="menu.jsp"/>
    <br/>

    <fieldset>
        <c:url value="/auth/login" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <c:if test="${error != null}">
                <p class="error">
                    ${error}
                </p>
            </c:if>
            <c:if test="${logout != null}">
                <p class="error">
                    ${logout}
                </p>
            </c:if>
            <p>
                <label for="username">Username</label>
                <input type="text" id="username" name="j_username"/>
            </p>
            <p>
                <label for="password">Password</label>
                <input type="password" id="password" name="j_password"/>
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn">Log in</button>
        </form>
    </fieldset>
</body>
</html>