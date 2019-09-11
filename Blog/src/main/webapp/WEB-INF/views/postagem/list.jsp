<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/14/2018
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Postagens</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <script type="text/javascript" src="<c:url value="/js/jquery-2.1.4.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/postagem.js"/>"></script>
</head>
<body>
    <c:import url="../menu.jsp"/>
    <fieldset>
        <legend>Lista de Postagens</legend>
        <div>
            <input id="search" type="search" placeholder="Busca por título" value="">
        </div>
        <table id="table-ajax" class="table" title="${autorId != null ? autorId : 0}">
            <%--<tr>
                <th>Código</th>
                <th>Título do Post</th>
                <th>Permalink</th>
                <th>Data de Postagem</th>
                <th>Autor</th>
                <th>Categorias</th>
                <th>Ação</th>
            </tr>
            <tbody id="tbody">--%>
            <jsp:include page="table-rows.jsp"/>
            <%--</tbody>--%>
        </table>

        <%--  FOI PARA TABLE-ROWS
        <div align="center">
            <c:forEach var="p" begin="1" end="${page.totalPages}">
                <c:choose>
                    <c:when test="${(p - 1) eq page.number}">
                        <button id="button_${p}" disabled="disabled" value="${p}">${p}</button>
                    </c:when>
                    <c:otherwise>
                        <button id="button_${p}" value="${p}">${p}</button>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        --%>

        <div id="info"></div>

    </fieldset>
</body>
</html>