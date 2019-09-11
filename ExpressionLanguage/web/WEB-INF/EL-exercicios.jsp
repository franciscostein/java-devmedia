<%@ page import="java.util.List" %>
<%@ page import="br.edu.alomundo.modelo.Pessoa" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 18-Jul-18
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Exercicios Scriplets</title>
    </head>
    <body>

        <%! int cont; %>

        <%
            List<Pessoa> pessoas = new ArrayList<>();

            Pessoa pessoa = new Pessoa();
            pessoa.setIdade(24);
            pessoa.setNasc(new Date());
            pessoa.setNome("Leandro");
            pessoa.setPeso(82.5);

            pessoas.add(pessoa);

            pessoa = new Pessoa();
            pessoa.setIdade(19);
            pessoa.setNasc(new Date());
            pessoa.setNome("Ana");
            pessoa.setPeso(63);

            pessoas.add(pessoa);

            pessoa = new Pessoa();
            pessoa.setIdade(14);
            pessoa.setNasc(new Date());
            pessoa.setNome("Maria");
            pessoa.setPeso(43);

            pessoas.add(pessoa);

            //request.setAttribute("pessoas", pessoas); Escopo dura enquanto durar a requisição
            //session.setAttribute("pessoas", pessoas); Escopo dura enquanto durar a sessão
            //application.setAttribute("pessoas", pessoas); Escopo dura enquanto durar a aplicação
        %>

        <table width="500" cellpadding="5" cellspacing="0" align="center" border="1">
            <tr>
                <td>Nome</td>
                <td>Nascimento</td>
                <td>Idade</td>
                <td>Peso</td>
            </tr>

            <%
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String bgcolor = null;

                for (Pessoa pessoa1 : pessoas) {

                    if (cont % 2 == 0) {

                        bgcolor = "gainsboro";

                    } else {

                        bgcolor = "none";
                    }

                    request.setAttribute("pessoa1", pessoa1);
                    request.setAttribute("bgcolor", bgcolor);
            %>
                <tr style="background-color: ${bgcolor}">
                    <td>${pessoa1.nome}</td>
                    <td>${pessoa1.nasc}</td>
                    <td>${pessoa1.idade}</td>
                    <td>${pessoa1.peso}</td>
                </tr>
            <%
                    cont++;
                }
            %>
        </table>

    </body>
</html>