<%--
  Created by IntelliJ IDEA.
  User: Leandro
  Date: 24/07/2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<div class="cabecalho">
    <div class="logo">
        <a href="index.jsp">
            <img src="img/generic-logo.png" alt="Generic Logo">
        </a>
    </div>
    <ul>
        <li><a href="main?acao=index" class="${param.acao eq 'index' ? 'selected' : ''}">Home</a></li>
        <li><a href="main?acao=montagemCadastro" class="${param.acao eq 'cadastros' ? 'selected' : ''}">Cadastros</a></li>
        <li><a href="main?acao=consultas" class="${param.acao eq 'consultas' ? 'selected' : ''}">Consultas</a></li>
        <li><a href="main?acao=logout" class="${param.acao eq 'logout' ? 'selected' : ''}">Sair</a></li>
    </ul>
    <div class="boasVindas">
        Bem-vindo(a), <b style="color: gray">${sessionScope.usuario.usuario}</b>!
    </div>
</div>