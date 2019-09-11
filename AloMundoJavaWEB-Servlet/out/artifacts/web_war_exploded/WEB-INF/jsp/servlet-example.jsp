<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 22-Jul-18
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet's Examples</title>
</head>
<body>

    <form action="example" method="post">
        ${msgErro != null ? msgErro : ''}
        <table width="300" cellpadding="10" align="center">
            <tr>
                <td>Nome:</td>
                <td><input type="text" name="nome" value="${param.nome}"/></td>
            </tr>
            <tr>
                <td>Endereço:</td>
                <td><input type="text" name="endereco" value="${param.endereco}"/></td>
            </tr>
            <tr>
                <td>CPF:</td>
                <td><input type="text" name="cpf" value="${param.cpf}" maxlength="11"/></td>
            </tr>
            <tr>
                <td>Data Nasc:</td>
                <td><input type="text" name="nasc" value="${param.nasc}" title="Campo no formato dd/MM/yyyy"
                           maxlength="10"/></td>
            </tr>
            <tr>
                <td>Data Expedição:</td>
                <td><input type="text" name="exped" value="${param.exped}"/></td>
            </tr>
            <tr align="center">
                <td><input type="submit" value="Enviar"/></td>
                <td><input type="reset" value="Limpar"/></td>
            </tr>
        </table>
    </form>

</body>
</html>
