<%@ page import="br.edu.devmedia.crud.dto.UfDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.devmedia.crud.dto.CidadeDTO" %>
<%@ page import="br.edu.devmedia.crud.dto.PreferenciaMusicalDTO" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="br.edu.devmedia.crud.dto.PessoaDTO" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <title>Atualizar</title>
    <link rel="stylesheet" href="css/global.css"/>
    <script type="text/javascript">
        function popularComboCidades(comboEstados) {
            var idEstado = comboEstados.options[comboEstados.selectedIndex].value;
            var formCadastro = document.forms[0];
            formCadastro.action = 'main?acao=montagemCadastro&isEdit=true&getCidades=true&idEstado=' + idEstado;
            formCadastro.submit();
        }
        
        function atualizar() {
            var formCadastro = document.forms[0];
            formCadastro.action = 'main?acao=atualizarPessoa';
            formCadastro.submit();
        }
    </script>
</head>
<body>

<jsp:include page="cabecalho.jsp"/>
<h1>Atualização</h1>

<div class="main">
    <form method="post">
        <jsp:include page="msg.jsp"/>
        <fieldset>
            <legend>Atualização de Pessoa</legend>

            <input type="hidden" value="${pessoa != null ? pessoa.idPessoa : param.id_pessoa}" name="id_pessoa"/>
            <table cellpadding="5">
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nome" maxlength="45" value="${pessoa != null ? pessoa.nome : param.nome}"/></td>
                </tr>
                <tr>
                    <td>CPF:</td>
                    <td><input type="text" name="cpf" maxlength="11" value="${pessoa != null ? pessoa.cpf : pessoa.cpf}"/></td>
                </tr>
                <tr>
                    <td>Data Nascimento:</td>
                    <td><input type="text" name="dtNasc" maxlength="10" value="${pessoa != null ? pessoa.dtNasc : pessoa.dtNasc}"/></td>
                </tr>
                <tr>
                    <td>Sexo:</td>
                    <td>
                        <input type="radio" name="sexo" value="M" ${'M' eq (pessoa != null ? pessoa.sexo.toString() : param.sexo) ? 'checked' : ''}/> Masculino
                        <input type="radio" name="sexo" value="F" ${'F' eq (pessoa != null ? pessoa.sexo.toString() : param.sexo) ? 'checked' : ''}/> Feminino
                    </td>
                </tr>
                <tr>
                    <td>Preferências:</td>
                    <td>
                        <%
                            List<PreferenciaMusicalDTO> preferencias = (List<PreferenciaMusicalDTO>) session.getAttribute("listaPreferencias");
                            PessoaDTO pessoa = (PessoaDTO) request.getAttribute("pessoa");
                            String[] paramPrefs = request.getParameterValues("gostos");
                            List<Integer> idsPrefs = new ArrayList<>();
                            if (pessoa != null && pessoa.getPreferencias() != null) {
                                for (PreferenciaMusicalDTO p : pessoa.getPreferencias()) {
                                    idsPrefs.add(p.getIdPreferencia());
                                }
                            }
                            if (preferencias != null) {
                                for (PreferenciaMusicalDTO preferencia : preferencias) {
                        %>
                            <input type="checkbox" name="gostos" value="<%= preferencia.getIdPreferencia() %>"
                                <%
                                    if (pessoa != null) {
                                %>
                                    <%= idsPrefs.contains(preferencia.getIdPreferencia()) ? "checked" : "" %>/>
                                <%
                                    } else {
                                %>
                                    <%= paramPrefs != null && Arrays.asList(paramPrefs).contains(String.valueOf(preferencia.getIdPreferencia())) ? "checked" : "" %>/>
                                <%
                                    }
                                %>
                            <%= preferencia.getDescricao() %>
                        <%
                                }
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>Mini-biografia:</td>
                    <td>
                        <textarea rows="5" cols="35" name="miniBio" maxlength="100">${pessoa != null ? pessoa.miniBio : param.miniBio}</textarea>
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
                                    UfDTO ufDTO = null;
                                    if (pessoa != null)
                                        ufDTO = pessoa.getEndereco().getCidade().getUf();
                                    for (UfDTO uf : listaUF) {
                                %>
                                    <option value="<%= uf.getIdUf() %>"
                                        <% if (ufDTO != null) { %>
                                            <%= ufDTO != null && uf.getIdUf().equals(ufDTO.getIdUf()) ? "selected='selected'" : "" %>>
                                        <% } else { %>
                                        <%= request.getParameter("uf") != null && String.valueOf(uf.getIdUf()).equals(request.getParameter("uf")) ? "selected='selected'" : "" %>>
                                        <% } %>
                                        <%= uf.getDescricao() %>
                                    </option>
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
                                <option value="0">Selecione...</option>
                                <%
                                    List<CidadeDTO> listaCidades = (List<CidadeDTO>) request.getAttribute("listaCidades");
                                    CidadeDTO cidadeDTO = null;
                                    if (pessoa != null)
                                        cidadeDTO = pessoa.getEndereco().getCidade();
                                    if (listaCidades != null) {
                                        for (CidadeDTO cidade : listaCidades) {
                                %>
                                    <option value="<%= cidade.getIdCidade() %>"
                                            <%= cidadeDTO != null && cidade.getIdCidade().equals(cidadeDTO.getIdCidade()) ? "selected='selected'" : "" %>>
                                        <%= cidade.getDescricao() %>
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
                            <input type="hidden" name="id_endereco" value="${pessoa != null ? pessoa.endereco.idEndereco : param.id_endereco}"/>
                            <input type="text" name="logradouro" value="${pessoa != null ? pessoa.endereco.logradouro : param.logradouro}"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </fieldset>
        <input type="reset" value="Limpar"/>
        <input type="button" value="Atualizar" onclick="atualizar()"/>
    </form>
</div>

<jsp:include page="rodape.jsp"/>

</body>
</html>