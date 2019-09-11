<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/12/2018
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Editar Avatar</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
<c:import url="../menu.jsp"/>
<br/>

<c:url var="save" value="/avatar/update?${_csrf.parameterName}=${_csrf.token}"/>
<form:form modelAttribute="avatar" action="${save}" method="post" enctype="multipart/form-data">

    <form:hidden path="id"/>
    <fieldset class="grupo">
        <legend>Editar Avatar</legend>
        <div class="campo">
            <form:label path="file">Avatar</form:label>
            <form:input type="file" path="file"/>
            <form:errors path="file" cssClass="errror"/>
        </div>
        <div>
            <input type="submit" value="Salvar">
            <input type="reset" value="Limpar">
        </div>
    </fieldset>
</form:form>

</body>
</html>
