<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/12/2018
  Time: 10:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset style="font-family: monospace; font-size: 10pt;">
    <legend>Menu</legend>
    <nav>
        <a href="<c:url value="/Blog/"/>">Home</a> <%-- No curso usa só / , mas aqui não funcionou --%>
    </nav>
    <nav>
        <security:authorize access="hasAnyAuthority('ADMIN', 'AUTOR', 'LEITOR')">
            |    <a href="<c:url value="/usuario/perfil/${logado.id}"/>">Perfil do Usuários</a>
        </security:authorize>
        <security:authorize access="hasAuthority('ADMIN')">
            |    <a href="<c:url value="/usuario/list"/>">Lista de Usuários</a>
        </security:authorize>
    </nav>
    <nav>
        <security:authorize access="hasAuthority('AUTOR')">
            |    <a href="<c:url value="/autor/add"/>">Dados do Autor</a>
        </security:authorize>
        <security:authorize access="hasAuthority('ADMIN')">
            |    <a href="<c:url value="/autor/list"/>">Lista de Autores</a>
        </security:authorize>
    </nav>
    <nav>
        <security:authorize access="hasAuthority('AUTOR')">
            |    <a href="<c:url value="/postagem/add"/>">Nova Postagem</a>
            |    <a href="<c:url value="/postagem/ajax/add"/>">Nova Postagem Ajax</a>
            |    <a href="<c:url value="/postagem/list/${logado.id}"/>">Lista de Postagens</a>
        </security:authorize>
        <security:authorize access="hasAuthority('ADMIN')">
            |    <a href="<c:url value="/postagem/list"/>">Lista de Postagens</a>
        </security:authorize>
    </nav>
    <nav>
        <security:authorize access="hasAnyAuthority('ADMIN', 'AUTOR')">
            |    <a href="<c:url value="/categoria/add"/>">Categorias</a>
        </security:authorize>
    </nav>
    <nav class="login">

        <c:if test="${ logado == null }">
            <a href="<c:url value="/auth/form"/>">Entrar</a>
            <a href="<c:url value="/usuario/add"/>">Cadastrar-se</a>
        </c:if>

        <security:authorize access="hasAnyAuthority('ADMIN', 'AUTOR', 'LEITOR')">
            <form action="<c:url value="/logout"/>" method="post">
                <security:csrfInput/>
                <button type="submit">Sair</button>
            </form>
        </security:authorize>
    </nav>
</fieldset>
</body>
</html>