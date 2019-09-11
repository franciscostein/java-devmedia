<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 03/05/2018
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul style="list-type: nome;">
    <c:url var="depAdd" value="/departamento/add"/>
    <li>
        <a href="${depAdd}" title="Departamentos">
            <img src="<c:url value="/files/anigif_enhanced-8808-1411683526-35_preview.gif"/>" alt="" width="15px" height="15px">
            Departamentos
        </a>
    </li>

    <c:url var="cargoAdd" value="/cargo/add"/>
    <li>
        <a href="${cargoAdd}" title="Cargos">
            <img src="<c:url value="/img/anigif_enhanced-8808-1411683526-35_preview.gif"/>" alt="" width="15px" height="15px">
            Cargos
        </a>
    </li>

    <c:url var="funcAdd" value="/funcionario/add"/>
    <li>
        <a href="${funcAdd}" title="Funcionários">
            <img src="<c:url value="/images/anigif_enhanced-8808-1411683526-35_preview.gif"/>" alt="" width="15px" height="15px">
            Funcionarios
        </a>
    </li>
</ul>