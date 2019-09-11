<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 28/03/2018
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>Error Page</h1>
    <p>${mensagem}</p>
    <div>
        <button onclick="javascript:history.back();">Voltar</button>
    </div>

    <!--
        Falha ao acessar a URL: ${url}
        Exceção: ${excecao}
    -->
</body>
</html>
