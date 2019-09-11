<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/14/2018
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categoria</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
</head>
<body>
<c:import url="../menu.jsp"/>

<c:url var="save" value="/categoria/save"/>
<form:form modelAttribute="categoria" action="${save}" method="post">
    <form:hidden path="id"/>
    <fieldset class="grupo">
        <legend>Cadastro de Categoria</legend>
        <div class="campo">
            <form:label path="descricao">Descrição da Categoria</form:label><br>
            <form:input path="descricao" type="text" required="true" size="30"/>
            <form:errors path="descricao" cssClass="error"/>
        </div>
        <div>
            <input type="submit" value="Salvar">
            <input type="reset" value="Limpar">
        </div>
    </fieldset>
</form:form>

<fieldset class="grupo">
    <legend>Lista de Categorias</legend>
    <table class="table">
        <tr>
            <th>Código</th>
            <th>Descrição</th>
            <th>Permalink</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="categoria" items="${page.content}" varStatus="i">
            <tr bgcolor='${i.count % 2 != 0 ? '#f1f1f1' : 'white'}'>
                <td>${categoria.id}</td>
                <td>${categoria.descricao}</td>
                <td>${categoria.permalink}</td>
                <td>
                    <c:url var="update" value="/categoria/update/${categoria.id}"/>
                    <a href="${update}" title="Editar">&#9445</a>
                    <c:url var="delete" value="/categoria/delete/${categoria.id}"/>
                    <a href="${delete}" title="Excluir">&#9447</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="../paginacao.jsp"/>
</fieldset>
</body>
</html>