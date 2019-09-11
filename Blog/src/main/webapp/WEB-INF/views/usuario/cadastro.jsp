<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/08/2018
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Usuário</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
<c:import url="../menu.jsp"/>

<c:url var="save" value="/usuario/save?${_csrf.parameterName}=${_csrf.token}"/> <%-- Trabalhando com SpringSecurity precisa de ${_csrf.parameterName}=${_csrf.token} --%>
<form:form modelAttribute="usuario" action="${save}" method="post" enctype="multipart/form-data">

    <form:hidden path="id"/>
    <fieldset>
        <legend> Cadastro de Usuário </legend>
        <div class="campo">
            <form:label path="nome">Nome do Usuário</form:label>
            <br/>
            <!--No spring não usa getNome ou setNome, só o nome da variável-->
            <form:input path="nome" type="text" value="${nome}"/>
            <form:errors path="nome" cssClass="error"/>
        </div>
        <br/>
        <div class="campo">
            <form:label path="email">E-mail</form:label>
            <br/>
            <form:input path="email" type="email" value="${email}"/>
            <form:errors path="email" cssClass="error"/>
        </div>
        <br/>
        <div class="campo">
            <form:label path="senha">Senha</form:label>
            <br/>
            <form:password path="senha"/>
            <form:errors path="senha" cssClass="error"/>
        </div>
        <br/>
        <div class="campo">
            <form:label path="file">Avatar</form:label>
            <form:input type="file" path="file"/>
            <form:errors path="file" cssClass="error"/>
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