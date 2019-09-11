<%@ page import="br.edu.devmedia.crud.dto.UfDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.devmedia.crud.dto.CidadeDTO" %><%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 24-Jul-18
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cadastros</title>
    <link rel="stylesheet" href="css/global.css"/>
    <script type="text/javascript">
        function init() {
            document.getElementById('uf').value = ${param.idEstado != null ? param.idEstado : '0'};
        }

        function popularComboCidades(comboEstados) {
            var idEstado = comboEstados.options[comboEstados.selectedIndex].value;
            location.href = 'main?acao=montagemCadastro&getCidades=true&idEstado=' + idEstado;
        }
    </script>
</head>
<body onload="init()">

<jsp:include page="cabecalho.jsp"/>
<h1>Cadastros</h1>

<div class="main">
    <form action="">
        <fieldset>
            <legend>Cadastro de Pessoa</legend>

            <table cellpadding="5">
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nome" /></td>
                </tr>
                <tr>
                    <td>Endereço:</td>
                    <td><input type="text" name="endereco" /></td>
                </tr>
                <tr>
                    <td>CPF:</td>
                    <td><input type="text" name="cpf" /></td>
                </tr>
                <tr>
                    <td>Data Nascimento:</td>
                    <td><input type="text" name="dtNasc" /></td>
                </tr>
                <tr>
                    <td>Sexo:</td>
                    <td><input type="radio" name="sexo" value="M" checked="checked"/> Masculino
                        <input type="radio" name="sexo" value="F" /> Feminino</td>
                </tr>
                <tr>
                    <td>Preferências:</td>
                    <td>
                        <input type="checkbox" name="gostos" value="jazz"/> Jazz
                        <input type="checkbox" name="gostos" value="blues"/> Blues
                        <input type="checkbox" name="gostos" value="rock"/> Rock
                        <input type="checkbox" name="gostos" value="soul"/> Soul
                    </td>
                </tr>
                <tr>
                    <td>Mini-biografia:</td>
                    <td>
                        <textarea rows="5" cols="35" name="miniBio"></textarea>
                    </td>
                </tr>
            </table>

            <fieldset>
                <legend>Endereço</legend>

                <table cellpadding="5">
                    <tr>
                        <td>UF:</td>
                        <td>
                            <select name="uf" id="uf" onchange="popularComboCidades(this)">
                                <option value="0">Selecione...</option>
                                <%
                                    List<UfDTO> listaUF = (List<UfDTO>) session.getAttribute("listaUF");
                                    for (UfDTO uf : listaUF) {
                                %>
                                <option value="<%=uf.getIdUf()%>"><%=uf.getDescricao()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Cidade:</td>
                        <td>
                            <select name="cidade">
                                <option>Selecione...</option>
                                <%
                                    List<CidadeDTO> listaCidades = (List<CidadeDTO>) request.getAttribute("listaCidades");
                                    if (listaCidades != null) {
                                        for (CidadeDTO cidade : listaCidades) {
                                %>
                                <option value="<%=cidade.getIdCidade()%>">
                                    <%=cidade.getDescricao()%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Logradouro:</td>
                        <td>
                            <input type="text" name="logradouro"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </fieldset>
        <input type="reset" value="Limpar"/>
        <input type="submit" value="Cadastrar"/>
    </form>
</div>

<jsp:include page="rodape.jsp"/>

</body>
</html>