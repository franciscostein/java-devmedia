<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 10-Aug-18
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://cewolf.sourceforge.net/taglib/cewolf.tld" prefix="cewolf"%>
<html>
<head>
    <title>Charts</title>
</head>
<body>

    <h1>Estatísticas</h1>
    
    <jsp:useBean id="viewNewspapers" class="br.edu.devmedia.crud.cewolf.ViewContDados"/>

    <!-- type="line" -->
    <!-- type="area" -->

    <cewolf:chart id="grafico_linha" type="horizontalbar3d" title="Estatísticas Jornais" xaxislabel="Jornais" yaxislabel="Visualizações">
        <cewolf:data>
            <cewolf:producer id="viewNewspapers"/>
        </cewolf:data>
    </cewolf:chart>

    <p>
        <cewolf:img renderer="cewolf" chartid="grafico_linha" width="1000" height="800"></cewolf:img>
    </p>

</body>
</html>
