package br.edu.devmedia.crud.command;

import br.edu.devmedia.crud.bo.PessoaBO;
import br.edu.devmedia.crud.dto.*;
import br.edu.devmedia.crud.exception.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FiltroCommandSemDisplayTag implements Command {

    private String proximo;

    private PessoaBO pessoaBO;

    @Override
    public String execute(HttpServletRequest request) {

        proximo = "consultas.jsp";
        this.pessoaBO = new PessoaBO();

        try {

            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String dtNasc = request.getParameter("dtNasc");
            String sexo = request.getParameter("sexo");
            String idUf = request.getParameter("uf");
            String idCidade = request.getParameter("cidade");
            String logradouro = request.getParameter("logradouro");

            String[] preferencias = request.getParameterValues("gostos");
            List<PreferenciaMusicalDTO> listaPreferencias = new ArrayList<>();

            if (preferencias != null) {

                for (String preferencia : preferencias) {

                    PreferenciaMusicalDTO preferenciaMusical = new PreferenciaMusicalDTO();
                    preferenciaMusical.setIdPreferencia(Integer.parseInt(preferencia));

                    listaPreferencias.add(preferenciaMusical);
                }
            }

            EnderecoDTO endereco = new EnderecoDTO();
            endereco.setLogradouro(logradouro);

            CidadeDTO cidade = new CidadeDTO();
            cidade.setIdCidade(idCidade != null ? Integer.parseInt(idCidade) : null);

            UfDTO uf = new UfDTO();
            uf.setIdUf(idUf != null ? Integer.parseInt(idUf) : null);

            PessoaDTO pessoa = new PessoaDTO();
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setDtNasc(dtNasc);
            pessoa.setSexo(sexo != null ? sexo.charAt(0) : ' ');
            pessoa.setPreferencias(listaPreferencias);

            cidade.setUf(uf);
            endereco.setCidade(cidade);
            pessoa.setEndereco(endereco);

            List<PessoaDTO> listaPessoas = pessoaBO.filtrar(pessoa);
            request.setAttribute("listaPessoas", listaPessoas);

            if (idUf != null) {

                List<CidadeDTO> listaCidades = pessoaBO.consultarCidadesPorEstado(uf.getIdUf());
                request.setAttribute("listaCidades", listaCidades);
            }

        } catch (NegocioException e) {

            request.setAttribute("msgErro", e.getMessage());
        }

        return proximo;
    }
}
