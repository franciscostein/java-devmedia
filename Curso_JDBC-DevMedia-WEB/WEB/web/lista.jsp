<%@ page import="java.util.List" %>
<%@ page import="jdbc.dto.PessoaDTO" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 01/28/2018
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listagem</title>
</head>
<body>
    <table border="1" cellpadding="5" cellspacing="0" width="500px">
        <tr style="color: white" bgcolor="black" align="center">
            <td>Nome</td>
            <td>Dt. Nasc</td>
            <td>Cidade</td>
            <td>Bairro</td>
            <td>UF</td>
            <td colspan="2">Ações</td>
        </tr>
        <%
            List<PessoaDTO> listaPessoas = (List<PessoaDTO>) request.getAttribute("listaPessoas");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (PessoaDTO pessoaDTO : listaPessoas) {
        %>
            <tr>
                <td><%= pessoaDTO.getNome() %></td>
                <td><%= dateFormat.format(pessoaDTO.getDtNascimento()) %></td>
                <td><%= pessoaDTO.getEnderecoDTO().getCidade() %></td>
                <td><%= pessoaDTO.getEnderecoDTO().getBairro() %></td>
                <td><%= pessoaDTO.getEnderecoDTO().getUfDTO().getSiglaUf() %></td>
                <td>
                    <a href="pessoa?acao=editar&id=<%= pessoaDTO.getIdPessoa() %>">Editar</a>
                </td>
                <td>
                    <a href="pessoa?acao=remover&idPessoa=<%= pessoaDTO.getIdPessoa() %>&idEndereco=<%= pessoaDTO.getEnderecoDTO().getIdEndereco() %>">Remover</a>
                </td>
            </tr>
        <%
            }
        %>
    </table>

    <br/>
    <%
        String msg = (String) request.getAttribute("msg");
    %>
    <%= msg != null ? msg : ""%>
</body>
</html>
