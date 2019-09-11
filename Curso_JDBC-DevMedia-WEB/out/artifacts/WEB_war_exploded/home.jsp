<%@ page import="java.util.List" %>
<%@ page import="jdbc.dto.UfDTO" %><%--
  Created by IntelliJ IDEA.
  User: leand
  Date: 01/25/2018
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <form action="pessoa?acao=cadastrar" method="post">
        <fieldset>
            <legend>Pessoa</legend>

            <table>
                <tr>
                    <td>Nome:</td>
                    <td>
                        <input type="text" name="nome">
                    </td>
                </tr>
                <tr>
                    <td>CPF:</td>
                    <td>
                        <input type="text" name="cpf">
                    </td>
                </tr>
                <tr>
                    <td>Dt. Nascimento:</td>
                    <td>
                        <input type="text" name="dtNasc">
                    </td>
                </tr>
            </table>
        </fieldset>

        <fieldset>
            <legend>Sexo</legend>

            <table>
                <tr>
                    <td>Sexo:</td>
                    <td>
                        <input type="radio" name="sexo" value="M" checked="checked"> Masculino
                        <input type="radio" name="sexo" value="F"> Feminino
                    </td>
                </tr>
            </table>
        </fieldset>

        <fieldset>
            <legend>Endereço</legend>

            <table>
                <tr>
                    <td>Logradouro:</td>
                    <td>
                        <input type="text" name="logradouro">
                    </td>
                </tr>
                <tr>
                    <td>Bairro:</td>
                    <td>
                        <input type="text" name="bairro">
                    </td>
                </tr>
                <tr>
                    <td>Cidade:</td>
                    <td>
                        <input type="text" name="cidade">
                    </td>
                </tr>
                <tr>
                    <td>Número:</td>
                    <td>
                        <input type="text" name="numero">
                    </td>
                </tr>
                <tr>
                    <td>CEP:</td>
                    <td>
                        <input type="text" name="cep">
                    </td>
                </tr>
                <tr>
                    <td>UF:</td>
                    <td>
                        <select name="uf">
                            <%
                                List<UfDTO> listaUfs = (List<UfDTO>) session.getAttribute("listaUfs");

                                for (UfDTO ufDTO : listaUfs) {
                            %>
                                <option value="<%= ufDTO.getIdUf() %>">
                                    <%= ufDTO.getDescricao() %>
                                </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
            </table>
            <br/>
            <%
                String msg = (String) request.getAttribute("msg");
            %>
            <%= msg != null ? msg : ""%>
        </fieldset>
        <input type="submit" value="Cadastrar">
        <input type="reset" value="Limpar">
    </form>
</body>
</html>
