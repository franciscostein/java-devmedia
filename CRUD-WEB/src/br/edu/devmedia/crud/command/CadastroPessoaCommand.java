package br.edu.devmedia.crud.command;

import br.edu.devmedia.crud.bo.PessoaBO;
import br.edu.devmedia.crud.dto.*;
import br.edu.devmedia.crud.util.MensagemContantes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CadastroPessoaCommand implements Command {

    private String proximo;

    private PessoaBO pessoaBO;

    @Override
    public String execute(HttpServletRequest request) {

        pessoaBO = new PessoaBO();
        proximo = "cadastroPessoa.jsp";

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String dtNasc = request.getParameter("dtNasc");
        String sexo = request.getParameter("sexo");
        String miniBio = request.getParameter("miniBio");
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

        try {

            PessoaDTO pessoa = new PessoaDTO();
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setDtNasc(dtNasc);
            pessoa.setSexo(sexo != null ? sexo.charAt(0) : ' ');
            pessoa.setMiniBio(miniBio);
            pessoa.setPreferencias(listaPreferencias);

            EnderecoDTO endereco = new EnderecoDTO();
            endereco.setLogradouro(logradouro);

            CidadeDTO cidade = new CidadeDTO();
            cidade.setIdCidade(idCidade != null ? Integer.parseInt(idCidade) : null);

            UfDTO uf = new UfDTO();
            uf.setIdUf(idUf != null ? Integer.parseInt(idUf) : null);

            cidade.setUf(uf);
            endereco.setCidade(cidade);
            pessoa.setEndereco(endereco);

            boolean isValido = pessoaBO.validarPessoa(pessoa);

            if (!isValido) {

                request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PESSOA_DADOS_INVALIDOS);

            } else {

                pessoaBO.cadastrarPessoa(pessoa);
                proximo = "main?acao=consultas";
                request.setAttribute("msgSucesso", MensagemContantes.MSG_SUCESSO_CADASTRO_PESSOA);
            }

        } catch (Exception e) {

            String idEstado = request.getParameter("uf");
            proximo = "main?acao=montagemCadastro&getCidades=true&idEstado=" + idEstado;
            request.setAttribute("msgErro", e.getMessage());
        }

        return proximo;
    }
}
