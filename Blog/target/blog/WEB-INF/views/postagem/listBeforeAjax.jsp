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
</head>
<body>
<c:import url="../menu.jsp"/>
<fieldset>
    <legend>Lista de Postagens</legend>
    <table class="table">
        <tr>
            <th>Código</th>
            <th>Título do Post</th>
            <th>Permalink</th>
            <th>Data de Postagem</th>
            <th>Autor</th>
            <th>Categorias</th>
            <th>Ação</th>
        </tr>

        <!-- ESSA PARTE ANTES DO AJAX E JS -->
        <c:forEach var="postagem" items="${page.content}" varStatus="i">
            <tr bgcolor="${i.count % 2 != 0 ? '#f1f1f1' : 'white'}">
                <td>${postagem.id}</td>
                <td>${postagem.titulo}</td>
                <td>${postagem.permalink}</td>
                <td>
                    <fmt:parseDate var="date" value="${postagem.dataPostagem}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
                    <fmt:formatDate value="${date}" type="both"/>
                </td>
                <td>${postagem.autor.nome}</td>
                <td>
                    <c:forEach var="c" items="${postagem.categorias}">
                        [ ${c.descricao} ]
                    </c:forEach>
                </td>
                <td>
                    <c:url var="update" value="/postagem/update/${postagem.id}"/>
                    <a href="${update}" title="Editar">&#9445</a>
                    <c:url var="delete" value="/postagem/delete/${postagem.id}"/>
                    <a href="${delete}" title="Excluir">&#9447</a>
                </td>
            </tr>
        </c:forEach>
        <!-- Até aqui -->

    </table>
    <c:import url="../paginacao.jsp"/>
</fieldset>
</body>
</html>