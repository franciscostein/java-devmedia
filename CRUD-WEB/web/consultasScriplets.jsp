<%@ page import="java.util.List" %>
<%@ page import="br.edu.devmedia.crud.dto.PessoaDTO" %>
<%@ page import="br.edu.devmedia.crud.dto.PreferenciaMusicalDTO" %><%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 24-Jul-18
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultas</title>
    <link rel="stylesheet" href="css/global.css"/>
</head>
<body>

    <jsp:include page="cabecalho.jsp"/>

    <div class="main">
        <form action="">
            <jsp:include page="msg.jsp"/>

            <fieldset>
                <legend>Consultas</legend>
                <h1>Consultas</h1>

                <table width="100%" border="1" cellspacing="0" cellpadding="5">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Sexo</th>
                            <th>Dt.Nasc</th>
                            <th>Endereço</th>
                            <th>Cidade</th>
                            <th>UF</th>
                            <th colspan="3">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        List<PessoaDTO> listaPessoas = (List<PessoaDTO>) request.getAttribute("listaPessoas");
                        for (PessoaDTO pessoa : listaPessoas) {
                    %>
                        <tr>
                            <td class="alignCenter"><%= pessoa.getIdPessoa() %></td>
                            <td class="alignLeft"><%= pessoa.getNome() %></td>
                            <td class="alignCenter"><%= pessoa.getCpf() %></td>
                            <td class="alignCenter"><%= pessoa.getSexo() %></td>
                            <td class="alignCenter"><%= pessoa.getDtNasc() %></td>
                            <td class="alignLeft"><%= pessoa.getEndereco().getLogradouro() %></td>
                            <td class="alignLeft"><%= pessoa.getEndereco().getCidade().getDescricao() %></td>
                            <td class="alignLeft"><%= pessoa.getEndereco().getCidade().getUf().getDescricao() %></td>
                            <td class="alignCenter">
                                <%
                                    String preferencias = "";
                                    for (PreferenciaMusicalDTO preferencia : pessoa.getPreferencias()) {
                                        preferencias += "[" + preferencia.getDescricao() + "]";
                                    }
                                %>
                                <a href="javascrip:void(0)" title="Preferências" onclick="alert('<%= preferencias %>');">
                                    <img src="img/music_32.png" alt="Preferências">
                                </a>
                            </td>
                            <td class="alignCenter">
                                <a href="main?acao=editarPessoa&id_pessoa=<%= pessoa.getIdPessoa() %>" title="Editar">
                                    <img src="img/Edit-32.png" alt="Editar">
                                </a>
                            </td>
                            <td class="alignCenter">
                                <a href="main?acao=removerPessoa&id_pessoa=<%= pessoa.getIdPessoa() %>" title="Remover">
                                    <img src="img/delete-32.png" alt="Remover">
                                </a>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </fieldset>
        </form>
    </div>

    <jsp:include page="rodape.jsp"/>

</body>
</html>