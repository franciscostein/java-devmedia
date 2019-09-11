<%@ page import="java.util.List" %>
<%@ page import="jdbc.dto.UfDTO" %>
<%@ page import="jdbc.dto.PessoaDTO" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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
    <form action="pessoa?acao=atualizar" method="post">
        <fieldset>
            <legend>Pessoa</legend>

            <br/>
            <%
                String msg = (String) request.getAttribute("msg");

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                PessoaDTO pessoaDTO = (PessoaDTO) request.getAttribute("pessoaDTO");
            %>
            <%= msg != null ? msg : ""%>
            <br/>

            <input type="hidden" name="id" value="<%= pessoaDTO.getIdPessoa() %>">
            <input type="hidden" name="idEndereco" value="<%= pessoaDTO.getEnderecoDTO().getIdEndereco() %>">
            <input type="hidden" name="idUF" value="<%= pessoaDTO.getEnderecoDTO().getUfDTO().getIdUf() %>">

            <table>
                <tr>
                    <td>Nome:</td>
                    <td>
                        <input type="text" name="nome" value="<%= pessoaDTO.getNome() %>">
                    </td>
                </tr>
                <tr>
                    <td>CPF:</td>
                    <td>
                        <input type="text" name="cpf"value="<%= pessoaDTO.getCpf() %>">
                    </td>
                </tr>
                <tr>
                    <td>Dt. Nascimento:</td>
                    <td>
                        <input type="text" name="dtNasc"value="<%= dateFormat.format(pessoaDTO.getDtNascimento()) %>">
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
                        <% if (pessoaDTO.getSexo() == 'M') { %>
                            <input type="radio" name="sexo" value="M" checked="checked"> Masculino
                            <input type="radio" name="sexo" value="F"> Feminino
                        <% } else {%>
                            <input type="radio" name="sexo" value="M"> Masculino
                            <input type="radio" name="sexo" value="F" checked="checked"> Feminino
                        <%}%>
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
                        <input type="text" name="logradouro" value="<%= pessoaDTO.getEnderecoDTO().getLogradouro() %>">
                    </td>
                </tr>
                <tr>
                    <td>Bairro:</td>
                    <td>
                        <input type="text" name="bairro" value="<%= pessoaDTO.getEnderecoDTO().getBairro() %>">
                    </td>
                </tr>
                <tr>
                    <td>Cidade:</td>
                    <td>
                        <input type="text" name="cidade" value="<%= pessoaDTO.getEnderecoDTO().getCidade() %>">
                    </td>
                </tr>
                <tr>
                    <td>Número:</td>
                    <td>
                        <input type="text" name="numero" value="<%= pessoaDTO.getEnderecoDTO().getNumero() %>">
                    </td>
                </tr>
                <tr>
                    <td>CEP:</td>
                    <td>
                        <input type="text" name="cep" value="<%= pessoaDTO.getEnderecoDTO().getCep() %>">
                    </td>
                </tr>
                <tr>
                    <td>UF:</td>
                    <td>
                        <select name="uf">
                            <%
                                List<UfDTO> listaUfs = (List<UfDTO>) session.getAttribute("listaUfs");

                                for (UfDTO ufDTO : listaUfs) {

                                    if (ufDTO.getIdUf().equals(pessoaDTO.getEnderecoDTO().getUfDTO().getIdUf())) {
                            %>
                                <option value="<%= ufDTO.getIdUf() %>" selected="selected">
                                    <%= ufDTO.getDescricao() %>
                                </option>
                            <%
                                } else {
                            %>
                                <option value="<%= ufDTO.getIdUf() %>">
                                    <%= ufDTO.getDescricao() %>
                                </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                </tr>
            </table>
        </fieldset>
        <input type="submit" value="Atualizar">
        <input type="reset" value="Limpar">
    </form>
</body>
</html>
