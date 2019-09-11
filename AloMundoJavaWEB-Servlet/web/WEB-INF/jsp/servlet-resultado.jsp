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
    <title>Servlet's Result</title>
</head>
<body>

    <form>
        <table width="300" cellpadding="10" align="center">
            <tr>
                <td>Nome:</td>
                <td><input type="text" name="nome" disabled="disabled" value="${pessoa.nome}"/></td>
            </tr>
            <tr>
                <td>Endereço:</td>
                <td><input type="text" name="endereco"  disabled="disabled" value="${pessoa.endereco}"/></td>
            </tr>
            <tr>
                <td>CPF:</td>
                <td><input type="text" name="cpf"  disabled="disabled" value="${pessoa.cpf}"/></td>
            </tr>
            <tr>
                <td>Data Nasc:</td>
                <td><input type="text" name="nasc"  disabled="disabled" value="${pessoa.dtNasc}"/></td>
            </tr>
        </table>
        Última atualização: ${param.data}
    </form>

</body>
</html>
