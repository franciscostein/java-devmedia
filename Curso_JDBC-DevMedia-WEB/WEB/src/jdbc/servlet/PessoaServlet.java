package jdbc.servlet;

import jdbc.bo.PessoaBO;
import jdbc.dto.EnderecoDTO;
import jdbc.dto.PessoaDTO;
import jdbc.dto.UfDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

//@WebServlet(name = "/pessoa")
@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {

    //@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String proxPage = "home.jsp";

        try {

            PessoaBO pessoaBO = new PessoaBO();

            if (acao.equals("cadastrar")) {

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String cpf = request.getParameter("cpf");
                String nome = request.getParameter("nome");
                String dtNasc = request.getParameter("dtNasc");
                String sexo = request.getParameter("sexo");
                String logradouro = request.getParameter("logradouro");
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");
                String numero = request.getParameter("numero");
                String cep = request.getParameter("cep");
                String uf = request.getParameter("uf");

                PessoaDTO pessoaDTO = new PessoaDTO();

                pessoaDTO.setCpf(Long.parseLong(cpf));
                pessoaDTO.setDtNascimento(dateFormat.parse(dtNasc));

                EnderecoDTO enderecoDTO = new EnderecoDTO();

                enderecoDTO.setBairro(bairro);
                enderecoDTO.setCep(Integer.parseInt(cep));
                enderecoDTO.setCidade(cidade);
                enderecoDTO.setLogradouro(logradouro);
                enderecoDTO.setNumero(Long.parseLong(numero));

                UfDTO ufDTO = new UfDTO();

                ufDTO.setIdUf(Integer.parseInt(uf));

                enderecoDTO.setUfDTO(ufDTO);

                pessoaDTO.setEnderecoDTO(enderecoDTO);
                pessoaDTO.setNome(nome);
                pessoaDTO.setSexo(sexo.charAt(0));

                pessoaBO.cadatrar(pessoaDTO);

                request.setAttribute("msg", "Cadastro efetuado com sucesso!");

                proxPage = "pessoa?acao=listar";

            } else if (acao.equals("listar")) {

                List<PessoaDTO> lista = pessoaBO.listagem();

                request.setAttribute("listaPessoas", lista);

                proxPage = "lista.jsp";

            } else if (acao.equals("editar")) {

                String id = request.getParameter("id");

                PessoaDTO pessoaDTO = pessoaBO.buscaPorId(Integer.parseInt(id));

                request.setAttribute("pessoaDTO", pessoaDTO);

                proxPage = "edicao.jsp";

            } else if (acao.equals("atualizar")) {

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String idPessoa = request.getParameter("id");
                String idEndereco = request.getParameter("idEndereco");
                //String idUF = request.getParameter("idUF");
                String cpf = request.getParameter("cpf");
                String nome = request.getParameter("nome");
                String dtNasc = request.getParameter("dtNasc");
                String sexo = request.getParameter("sexo");
                String logradouro = request.getParameter("logradouro");
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");
                String numero = request.getParameter("numero");
                String cep = request.getParameter("cep");
                String uf = request.getParameter("uf");

                PessoaDTO pessoaDTO = new PessoaDTO();

                pessoaDTO.setIdPessoa(Integer.parseInt(idPessoa));
                pessoaDTO.setCpf(Long.parseLong(cpf));
                pessoaDTO.setDtNascimento(dateFormat.parse(dtNasc));

                EnderecoDTO enderecoDTO = new EnderecoDTO();

                enderecoDTO.setIdEndereco(Integer.parseInt(idEndereco));
                enderecoDTO.setBairro(bairro);
                enderecoDTO.setCep(Integer.parseInt(cep));
                enderecoDTO.setCidade(cidade);
                enderecoDTO.setLogradouro(logradouro);
                enderecoDTO.setNumero(Long.parseLong(numero));

                UfDTO ufDTO = new UfDTO();

                ufDTO.setIdUf(Integer.parseInt(uf));

                enderecoDTO.setUfDTO(ufDTO);

                pessoaDTO.setEnderecoDTO(enderecoDTO);
                pessoaDTO.setNome(nome);
                pessoaDTO.setSexo(sexo.charAt(0));

                pessoaBO.atualizar(pessoaDTO);

                proxPage = "pessoa?acao=listar";

            } else if (acao.equals("remover")) {

                String idPessoa = request.getParameter("idPessoa");
                String idEndereco = request.getParameter("idEndereco");

                pessoaBO.removePessoa(Integer.parseInt(idPessoa), Integer.parseInt(idEndereco));

                request.setAttribute("msg", "Pessoa removida com sucesso!");

                proxPage = "pessoa?acao=listar";
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", e.getMessage());
        }

        request.getRequestDispatcher(proxPage).forward(request, response);
    }
}
