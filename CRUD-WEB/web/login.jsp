<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 24/07/2018
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - CRUD WEB</title>
    <link rel="stylesheet" href="css/global.css"/>
</head>
<body>

    <form method="post" id="login_form" action="main?acao=login">

        <jsp:include page="msg.jsp"/>

        <fieldset id="fieldset_login">
            <legend>Login do Sistema</legend>

            <div class="campo">
                <div class="label">
                    <label for="login">Login</label>
                </div>
                <input type="text" id="login" name="login" maxlength="15" value="${param.login}"/>
            </div>

            <div class="campo">
                <div class="label">
                    <label for="senha">Senha</label>
                </div>
                <input type="password" id="senha" name="senha" maxlength="15" value="${param.senha}"/>
            </div>

            <div class="campo">
                <input type="submit" value="Logar"/>
            </div>
            <div class="campo">
                <a href="">Esqueci a Senha</a>
            </div>
        </fieldset>
    </form>

</body>
</html>
