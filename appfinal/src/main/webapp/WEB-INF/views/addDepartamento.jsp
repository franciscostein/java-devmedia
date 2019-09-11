<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 02/05/2018
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Departamento</title>
</head>
<body>
    <c:import url="menu.jsp"/>
    <c:url var="save" value="/departamento/save"/>
    <form:form modelAttribute="departamento" action="${save}" method="post">
        <form:hidden path="idDepartamento"/>
        <fieldset style="width: 300px; margin: 0 auto;">
            <legend>Departamentos</legend>
            <div>
                <form:label path="departamento">Departamento</form:label>
                <br>
                <form:input path="departamento" type="text" required="true"/>
            </div>
            <br>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpar">
            </div>
        </fieldset>
    </form:form>

    <fieldset style="width: 300px; margin: 0 auto;">
        <legend>Departamento</legend>
        <table style="width: 300px">
            <tr>
                <th>Código</th>
                <th>Descrição</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="d" items="${departamentos}" varStatus="i">
                <tr bgcolor="${i.count % 2 == 0 ? '#f1f1f1' : 'white'}">
                    <td>${d.idDepartamento}</td>
                    <td>${d.departamento}</td>
                    <td>
                        <c:url var="update" value="/departamento/update/${d.idDepartamento}"></c:url>
                        <a href="${update}" title="Ver/Editar">&#9445;</a>
                        |
                        <c:url var="delete" value="/departamento/delete/${d.idDepartamento}"></c:url>
                        <a href="${delete}" title="Deletar">&#9447;</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
</body>
</html>
