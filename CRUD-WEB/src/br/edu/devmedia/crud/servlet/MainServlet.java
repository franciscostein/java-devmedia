package br.edu.devmedia.crud.servlet;

import br.edu.devmedia.crud.command.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "main", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {

    private Map<String, Command> comandos = new HashMap<>();

    @Override   //Init dura toda a vida da sessão
    public void init() throws ServletException {    //Padrão command (Design patterns)

        comandos.put("login", new LoginCommand());
        comandos.put("montagemCadastro", new MontagemCadastroCommand());
        comandos.put("cadastroPessoa", new CadastroPessoaCommand());
        comandos.put("consultas", new ConsultasPessoaCommand());
        comandos.put("filtrar", new FiltroCommand());
        comandos.put("removerPessoa", new RemoverPessoaCommand());
        comandos.put("atualizarPessoa", new AtulizarPessoaCommand());
        comandos.put("editarPessoa", new EditarPessoaCommand());
        comandos.put("logout", new LogoutCommand());
        comandos.put("index", new IndexCommand());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String proxima = null;

        try {

            Command comando = verificarComando(acao);
            proxima = comando.execute(request);

        } catch (Exception e) {

            request.setAttribute("msgErro", e.getMessage());
        }

        request.getRequestDispatcher(proxima).forward(request, response);
    }

    private Command verificarComando(String acao) {

        Command comando = null;

        for (String key : comandos.keySet()) {

            if (key.equalsIgnoreCase(acao)) {

                comando = comandos.get(key);
            }
        }

        return comando;
    }
}
