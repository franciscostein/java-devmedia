<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 21/03/2018
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div align="center">
    [
    <c:forEach var="p" begin="1" end="${page.totalPages}">
        <c:choose>
            <c:when test="${(p - 1) eq page.number}">
                <label style="font-size: 18pt;">${p}</label>
            </c:when>
            <c:otherwise>
                <label>
                    <a href="<c:url value="${urlPagination}/${p}"/>">
                            ${p}
                    </a>
                </label>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    ]
</div>