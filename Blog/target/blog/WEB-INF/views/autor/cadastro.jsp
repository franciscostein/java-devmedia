<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 13/03/2018
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Autor</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
<c:import url="../menu.jsp"/>

<c:url var="save" value="/autor/save"/>
<form:form modelAttribute="autor" action="${save}" method="post">
    <form:hidden path="id"/>
    <fieldset>
        <legend> Cadastro de Autor </legend>
        <div class="campo">
            <form:label path="nome">Nome do Autor</form:label>
            <br/>
            <!--No spring não usa getNome ou setNome, só o nome da variável-->
            <form:input path="nome" type="text"/>
            <form:errors path="nome" cssClass="error"/>
        </div>
        <br/>
        <div class="campo">
            <form:label path="biografia">Biografia</form:label>
            <br/>
            <form:textarea path="biografia" cols="50" rows="10" type="text"/>
            <form:errors path="biografia" cssClass="error"/>
        </div>
        <br/>
        <div>
            <input type="submit" value="Salvar">
            <input type="reset" value="Limpar">
        </div>
    </fieldset>

</form:form>
</body>
</html>
