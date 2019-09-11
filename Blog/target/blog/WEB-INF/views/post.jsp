<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/15/2018
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>${postagem.titulo}</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
<fieldset class="header">
    <h1>Blog do Curso de Spring-DATA JPA ! Devmedia</h1>
</fieldset>

<c:import url="menu.jsp"/>
<br/>

<fieldset>

    <div>
        <div>
            <h2>${postagem.titulo}</h2>
            <p>Autor: <a href="<c:url value="/Blog/autor/${postagem.autor.id}/page/1"/>">${postagem.autor.nome}</a>
                <fmt:parseDate var="date" value="${postagem.dataPostagem}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
                | Data: <fmt:formatDate value="${date}" type="both"/>
            </p>
        </div>
        <div>
            <p class="post-texto">${postagem.texto}</p>
        </div>
        <div>
            <p class="post-categ">
                <span>Categorias: </span>
                <c:forEach var="c" items="${postagem.categorias}">
                    <a href="<c:url value="/Blog/categoria/${c.permalink}/page/1"/>" title="${c.descricao}">
                            ${c.descricao}
                    </a>
                </c:forEach>
            </p>
        </div>
    </div>
    <div class="post-autor">
        <img class="post-avatar" src="<c:url value="/avatar/load/${postagem.autor.usuario.avatar.id}"/>"/>
        <p><strong>${postagem.autor.nome}</strong></p>
        <p>${postagem.autor.biografia}</p>
    </div>
    <c:if test="${logado == null}">
        <p><em>Apenas usuários logados podem comentar</em></p>
    </c:if>
    <c:import url="comments.jsp"/>

</fieldset>
</body>
</html>