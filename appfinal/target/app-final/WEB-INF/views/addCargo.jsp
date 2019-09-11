<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 03/05/2018
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cargos</title>
</head>
<body>
    <c:import url="menu.jsp"/>
    <c:url var="save" value="/cargo/save"/>
    <form:form modelAttribute="cargo" action="${save}" method="post">
        <form:hidden path="idCargo"/>
        <fieldset style="width: 500px; margin: 0 auto;">
            <legend>Cargo</legend>
            <div>
                <form:label path="cargo">Cargo</form:label>
                <br>
                <form:input path="cargo" type="text" required="true"/>
            </div>
            <br>
            <div>
                <form:label path="departamento">Departamento</form:label>
                <br>
                <form:select path="departamento" required="true">
                    <form:option value="" label="--- Select ---"/>
                    <form:options items="${departamentos}" itemValue="idDepartamento" itemLabel="departamento"/>
                </form:select>
            </div>
            <br>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpar">
            </div>
        </fieldset>
    </form:form>

    <fieldset style="width: 500px; margin: 0 auto;">
        <legend>Cargo</legend>
        <table style="width: 500px">
            <tr>
                <th>Código</th>
                <th>Descrição</th>
                <th>Departamento</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="f" items="${cargos}" varStatus="i">
                <tr bgcolor="${i.count % 2 == 0 ? '#f1f1f1' : 'white'}">
                    <td>${f.idCargo}</td>
                    <td>${f.cargo}</td>
                    <td>${f.departamento.departamento}</td>
                    <td>
                        <c:url var="update" value="/cargo/update/${f.idCargo}"></c:url>
                        <a href="${update}" title="Ver/Editar">&#9445;</a>
                        |
                        <c:url var="delete" value="/cargo/delete/${f.idCargo}"></c:url>
                        <a href="${delete}" title="Deletar">&#9447;</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div align="center">
            <c:if test="${current != 1}">
                <a href="<c:url value="/cargo/page/${current - 1}"/>" title="previous">&laquo;</a>
            </c:if>
                ${curret}
            <c:if test="${current >= 1 && (current + 1) <= total}">
                <a href="<c:url value="/cargo/page/${current + 1}"/>" title="página(s) de ${total}">&raquo;</a>
            </c:if>
        </div>
    </fieldset>
</body>
</html>