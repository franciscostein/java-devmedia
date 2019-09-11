<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
      <title></title>
    </head>
    <body>

        <jsp:forward page="WEB-INF/scriplets-exercicios.jsp"/>

        <%!
            String nome;
            String dataNasc;
            String cidade;
        %>

        <td>

        <%
            nome = "Leandro";
            dataNasc = "11/03/1994";
            cidade = "Campinas";

            List<String> infs = new ArrayList<>();

            for (String inf : infs) {
        %>
            <tr> <%= inf %> </tr>
        <%
            }
        %>

        </td>
    </body>
</html>