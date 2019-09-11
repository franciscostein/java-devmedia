<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/14/2018
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Home</title>
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
        <form action="<c:url value="/Blog/search"/>" method="get">
            <input name="texto" type="search" placeholder="busca por palavra chave">
            <input type="submit" value="Localizar">
        </form>
    </div>
    <c:forEach var="p" items="${page.content}">
        <div>
            <div>
                <h2><a href="<c:url value="/Blog/${p.permalink}"/>" title="${p.titulo}">${p.titulo}</a></h2>
                <p>Autor: <a href="<c:url value="/Blog/autor/${p.autor.id}/page/1"/>">${p.autor.nome}</a>
                    |   <fmt:parseDate var="date" value="${p.dataPostagem}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
                    Data: <fmt:formatDate value="${date}" type="both"/>
                    |    # ${fn:length(p.comentarios)} Comentário(s)
                </p>
            </div>
            <div>
                    <%--<p>${p.texto}</p> DESSA FORMA MOSTRA O TEXTO INTEIRO, ABAIXO MOSTRA UM RESUMO--%>
                <p class="post-texto">
                    <c:forTokens var="resumo" items="${p.texto}" delims=" " begin="0" end="60">
                        ${resumo}
                    </c:forTokens><a href="<c:url value="/Blog/${p.permalink}"/>">[Leia Mais]</a>
                </p>
            </div>
            <div>
                <p class="post-categ">
                    <span>Categorias: </span>
                    <c:forEach var="c" items="${p.categorias}">
                        <a href="<c:url value="/Blog/categoria/${c.permalink}/page/1"/>" title="${c.descricao}">
                                ${c.descricao}
                        </a>
                    </c:forEach>
                </p>
            </div>
        </div>
    </c:forEach>
    <c:import url="paginacaoHome.jsp"/>
</fieldset>
</body>
</html>