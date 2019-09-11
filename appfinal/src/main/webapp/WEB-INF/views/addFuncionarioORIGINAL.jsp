<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 07/05/2018
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Funcionarios</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">

    <script type="text/javascript" src="<c:url value="/js/function.js"/>"/>
</head>
<body>
    <c:import url="menu.jsp"/>

    <fildset class='master'>
        <c:url var="save" value="/funcionario/save"/>
        <form:form modelAttribute="funcionario" action="${save}" method="post">
            <form:errors cssClass="errorblock" path="*" element="div"/>
            <form:hidden path="idFuncionario"/>
            <fieldset class='grupo'>
                <legend>Funcionario</legend>
                <div class='campo'>
                    <form:label path="nome">Nome</form:label>
                    <br>
                    <form:input path="nome" type="text" size="40" id="nome"/>
                </div>
                <div class='campo'>
                    <form:label path="salario">Salário</form:label>
                    <br>
                    <form:input path="salario" type="text" size="20"/>
                </div>
                <div class='campo'">
                    <form:label path="dataEntrada">Data de Entrada</form:label>
                    <br>
                    <form:input path="dataEntrada" type="date"/>
                </div>
                <div class='campo'>
                    <form:label path="dataSaida">Data de Saída</form:label>
                    <br>
                    <form:input path="dataSaida" type="date"/>
                </div>
                <input type="button" value="Localizar" onclick="localizarPorNome();">
            </fieldset>

            <fieldset class='grupo'>
                <legend>Cargo</legend>
                <div class='campo'>
                    <form:label path="cargo">Cargo</form:label>
                    <br>
                    <form:select id="cargo" path="cargo">
                        <form:option value="" label="--- Select ---"/>
                        <form:options items="${cargos}" itemValue="idCargo" itemLabel="cargo"/>
                    </form:select>
                    <input type="button" onclick="localizarPorCargo();" value="Localizar">
                </div>
            </fieldset>
            <br>
            <fieldset class='grupo'>
                <form:hidden path="endereco.idEndereco"/>
                <legend>Endereço</legend>
                <div class='campo'>
                    <form:label path="endereco.logradouro">Logradouro</form:label>
                    <br>
                    <form:input path="endereco.logradouro" type="text" size="30"/>
                    <form:errors cssClass="error" path="endereco.logradouro" element="div"/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.numero">Número</form:label>
                    <br>
                    <form:input path="endereco.numero" type="text" size="30"/>
                    <form:errors cssClass="error" path="endereco.numero" element="div"/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.complemento">Complemento</form:label>
                    <br>
                    <form:input path="endereco.complemento" type="text" size="30"/>
                    <div>&nbsp;</div>
                </div>
                <div class='campo'>
                    <form:label path="endereco.bairro">Bairro</form:label>
                    <br>
                    <form:input path="endereco.bairro" type="text" size="30"/>
                    <form:errors cssClass="error" path="endereco.bairro" element="div"/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.cidade">Cidade</form:label>
                    <br>
                    <form:input path="endereco.cidade" type="text" size="30"/>
                    <form:errors cssClass="error" path="endereco.cidade" element="div"/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.estado">Estado</form:label>
                    <br>
                    <form:input path="endereco.estado" type="text" size="30"/>
                    <form:errors cssClass="error" path="endereco.estado" element="div"/>
                </div>
            </fieldset>
            <br>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpar">
            </div>
        </form:form>
    </fildset>

    <fieldset class='master'>
        <legend>Funcionários</legend>
        <table style="width: 960px">
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Salário</th>
                <th>Data de Entrada</th>
                <th>Data de Saída</th>
                <th>Cargo</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="f" items="${funcionarios}" varStatus="i">
                <tr bgcolor="${i.count % 2 == 0 ? '#f1f1f1' : 'white'}">
                    <td>${f.idFuncionario}</td>
                    <td>${f.nome}</td>
                    <td>
                        <fmt:formatNumber value="${f.salario}" currencySymbol="R$" maxFractionDigits="2" type="currency"/>
                    </td>
                    <td>                                            <!--yyyy-MM-dd pois é o valor que está recebendo, ele detecta o valor do pais-->
                        <fmt:parseDate var="dtEntrada" value="${f.dataEntrada}" pattern="yyyy-MM-dd"/>
                        <fmt:formatDate value="${dtEntrada}" dateStyle="full"/>
                    </td>
                    <td>
                        <fmt:parseDate var="dtSaida" value="${f.dataSaida}" pattern="yyyy-MM-dd"/>
                        <fmt:formatDate value="${dtSaida}"/>
                    </td>
                    <td>${f.cargo.cargo}</td>
                    <td>
                        <c:url var="update" value="/funcionario/update/${f.idFuncionario}"></c:url>
                        <a href="${update}" title="Ver/Editar">&#9445;</a>
                        |
                        <c:url var="delete" value="/funcionario/update/${f.idFuncionario}"></c:url>
                        <a href="${delete}" title="Deletar">&#9447;</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
</body>
</html>