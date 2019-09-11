<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/14/2018
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Postagem</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
<c:import url="../menu.jsp"/>

<c:url var="save" value="/postagem/save"/>
<form:form modelAttribute="postagem" action="${save}" method="post">
    <form:hidden path="id"/>
    <fieldset>
        <legend> Cadastro de Postagem</legend>
        <div class="campo">
            <form:label path="titulo">Título do Post</form:label><br>
            <form:input path="titulo" type="text" size="60"/>
            <form:errors path="titulo" cssClass="error"/>
        </div>
        <div class="campo">
            <form:label path="texto">Texto do Post</form:label><br>
            <form:textarea path="texto" rows="15" cols="80"/>
            <form:errors path="texto" cssClass="error"/>
        </div>
        <div class="campo">
            <form:label path="categorias">Selecione a(s) Categoria(s)</form:label>
            <br/>
            <form:select path="categorias" multiple="true">
                <form:options items="${categorias}" itemValue="id" itemLabel="descricao"/>
            </form:select>
        </div>
        <div>
            <input type="submit" value="Salvar">
            <input type="reset" value="Limpar">
        </div>
    </fieldset>
</form:form>
</body>
</html>