<%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 03/15/2018
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="comentarios">
    <security:authorize access="hasAnyAuthority('ADMIN', 'AUTOR', 'LEITOR')">
        <c:url var="save" value="/comentario/save"/>
        <form:form modelAttribute="comentario" action="${save}" method="post">
            <input type="hidden" value="${postagem.permalink}" name="permalink">
            <div>
                <form:label path="texto">Digite seu comentário:</form:label>
                <form:textarea path="texto" rows="5" cols="80"/> <%--required="true" pelo HTML 5, mas estamos usando o Hibernate MVC logo abaixo--%>
                <form:errors path="texto" cssClass="error"/>
            </div>
            <br/>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpar">
            </div>
        </form:form>
    </security:authorize>
    <hr>
    <c:forEach var="c" items="${postagem.comentarios}">
        <div class="comentarios">
            <img class="comentarios-avatar" src="<c:url value="/avatar/load/${c.usuario.avatar.id}"/>">
            <em>
                    ${c.usuario.nome} -
                <fmt:parseDate var="date" value="${c.dataComentario}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
                <fmt:formatDate value="${date}" type="both"/>
            </em>
        </div>
        <p>${c.texto}</p>
    </c:forEach>
</div>