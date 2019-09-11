<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/12/2018
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Editar Usuário</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
    <c:import url="../menu.jsp"/>
    <c:url var="save" value="/usuario/update/senha"/>
    <form:form modelAttribute="usuario" action="${save}" method="post">
        <form:hidden path="id"/>
        <fieldset class="grupo">
            <legend>Editar Senha</legend>
            <div class="campo">
                <form:label path="senha">Senha</form:label>
                <form:password path="senha"/>
                <form:errors path="senha" cssClass="error"/>
            </div>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpar">
            </div>
        </fieldset>
    </form:form>

    <c:url var="save" value="/usuario/update"/>
    <form:form modelAttribute="usuario" action="${save}" method="post">

        <form:hidden path="id"/>
        <fieldset class="grupo">
            <legend> Editar Usuário Nome e/ou E-mail</legend>
            <div class="campo">
                <form:label path="nome">Nome do Usuário</form:label>
                <br/>
                <!--No spring não usa getNome ou setNome, só o nome da variável-->
                <form:input path="nome" type="text"/>
                <form:errors path="nome" cssClass="error"/>
            </div>
            <br/>
            <div class="campo">
                <form:label path="email">E-mail</form:label>
                <br/>
                <form:input path="email" type="email"/>
                <form:errors path="email" cssClass="error"/>
            </div>
            <br/>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpar">
            </div>
        </fieldset>
    </form:form>
    <security:authorize access="hasAuthority('ADMIN')">
        <c:url var="save" value="/usuario/update/perfil"/>
        <form:form modelAttribute="usuario" action="${save}" method="post">
            <form:hidden path="id"/>
            <fieldset class="grupo">
                <legend>Editar Perfil</legend>
                <div class="campo">
                    <form:label path="perfil">Perfil</form:label>
                    <form:select path="perfil" required="true">
                        <form:option value="ADMIN" label="ADMIN"/>
                        <form:option value="AUTOR" label="AUTOR"/>
                        <form:option value="LEITOR" label="LEITOR"/>
                    </form:select>
                </div>
                <div>
                    <input type="submit" value="Salvar">
                    <input type="reset" value="Limpar">
                </div>
            </fieldset>
        </form:form>
    </security:authorize>
</body>
</html>