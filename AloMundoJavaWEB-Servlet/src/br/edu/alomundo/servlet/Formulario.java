package br.edu.alomundo.servlet;

import br.edu.alomundo.converter.CPFConverter;
import br.edu.alomundo.converter.Converter;
import br.edu.alomundo.converter.DataConverter;
import br.edu.alomundo.dto.PessoaDTO;
import br.edu.alomundo.exception.ValidationException;
import br.edu.alomundo.util.Util;
import br.edu.alomundo.validator.CPFValidator;
import br.edu.alomundo.validator.DataValidator;
import br.edu.alomundo.validator.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/example")
public class Formulario extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        req.setAttribute("data", dateFormat.format(new Date()));

        String redirect = "servlet-example.jsp";

                                //Apenas um & = and forçando testar a segunda situação mesmo que a 1ª for false
        if (validarCampoObg(req, resp) & validarData(req) & validarCPF(req)) {

            redirect = "servlet-resultado.jsp";
            req.setAttribute("pessoa", gerarObjetoPessoa(req));;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
        dispatcher.forward(req, resp);
    }

    //Esse metodo não deve estar aqui pelo padrão de camadas
    private boolean validarData(HttpServletRequest req) {

        boolean retorno = false;

        try {

            Validator dataValidator = new DataValidator();
            String nasc = req.getParameter("nasc");
            String exped = req.getParameter("exped");

            Map<String, Object> valoresData = new HashMap<>();
            valoresData.put("Dt. Nasc", nasc);
            valoresData.put("Dt. Expedição", exped);

            if (dataValidator.validar(valoresData)) {

                retorno = true;
            }

        } catch (ValidationException e) {

            req.setAttribute("msgErro", Util.concatenaMensagensRequest(req, e, "msgErro"));
        }

        return retorno;
    }

    //Esse metodo não deve estar aqui pelo padrão de camadas
    private boolean validarCPF(HttpServletRequest req) {

        boolean retorno = false;

        try {

            String cpf = req.getParameter("cpf");

            Map<String, Object> valoresCPF = new HashMap<>();
            valoresCPF.put("CPF", cpf);

            if (new CPFValidator().validar(valoresCPF)) {

                retorno = true;
            }

        } catch (ValidationException e) {

            req.setAttribute("msgErro", Util.concatenaMensagensRequest(req, e, "msgErro"));
        }

        return retorno;
    }

    //Esse metodo não deve estar aqui pelo padrão de camadas
    private boolean validarCampoObg(HttpServletRequest request, HttpServletResponse response) {

        boolean retorno = true;

        String msgErro = "";

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String cpf = request.getParameter("cpf");
        String nasc = request.getParameter("nasc");
        String exped = request.getParameter("exped");

        if (nome == null || "".equals(nome)) {

            retorno = false;
            msgErro += "Campo Nome obrigatório<br/>";
        }

        if (endereco == null || "".equals(endereco)) {

            retorno = false;
            msgErro += "Campo Endereço obrigatório<br/>";
        }

        if (cpf == null || "".equals(cpf)) {

            retorno = false;
            msgErro += "Campo CPF obrigatório<br/>";
        }

        if (nasc == null || "".equals(nasc)) {

            retorno = false;
            msgErro += "Campo Dt. Nasc obrigatório<br/>";
        }

        if (exped == null || "".equals(exped)) {

            retorno = false;
            msgErro += "Campo Dt. Expedição obrigatório<br/>";
        }

        request.setAttribute("msgErro", msgErro);
        return retorno;
    }

    private PessoaDTO gerarObjetoPessoa(HttpServletRequest request) {

        Converter converterCPF = new CPFConverter();
        Converter converterData = new DataConverter();

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String cpf = request.getParameter("cpf");
        String nasc = request.getParameter("nasc");
        String exped = request.getParameter("exped");

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(nome);
        pessoaDTO.setEndereco(endereco);
        pessoaDTO.setCpf((Long) converterCPF.converter(cpf));
        pessoaDTO.setDtNasc((Date) converterData.converter(nasc));
        pessoaDTO.setDtExedicao((Date) converterData.converter(exped));

        return pessoaDTO;
    }

    /*@Override     NÃO UTILIZAR, FUNCIONA MAS É UMA MÁ PRÁTICA
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = req.getParameter("nome");
        String endereco = req.getParameter("endereco");
        String cpf = req.getParameter("cpf");
        String nasc = req.getParameter("nasc");

        PrintWriter writer = resp.getWriter();
        writer.println("Nome: " + nome);
        writer.println("Endereço: " + endereco);
        writer.println("CPF: " + cpf);
        writer.println("Nasc: " + nasc);
        writer.flush();
    }*/
}
