package br.edu.devmedia.jdbc.bo;

import br.edu.devmedia.jdbc.dao.PessoaDAO;
import br.edu.devmedia.jdbc.dto.EnderecoDTO;
import br.edu.devmedia.jdbc.dto.PessoaDTO;
import br.edu.devmedia.jdbc.exception.NegocioException;
import br.edu.devmedia.jdbc.exception.ValidacaoException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PessoaBO {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void cadatrar(PessoaDTO pessoaDTO) throws NegocioException {

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();

            pessoaDAO.inserir(pessoaDTO);

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public String[][] listagem(List<Integer> idPessoas) throws NegocioException {

        int numColumns = 10;
        String[][] listaRetorno = null;

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();

            List<PessoaDTO> lista = pessoaDAO.listarTodos();

            listaRetorno = new String[lista.size()][numColumns];

            for (int i = 0; i < lista.size(); i++) {

                PessoaDTO pessoa = lista.get(i);
                EnderecoDTO enderecoDTO = pessoa.getEnderecoDTO();

                listaRetorno[i][0] = pessoa.getIdPessoa().toString();
                idPessoas.add(pessoa.getIdPessoa());
                listaRetorno[i][1] = pessoa.getNome();
                listaRetorno[i][2] = pessoa.getCpf().toString();
                listaRetorno[i][3] = pessoa.getSexo() == 'M' ? "Masculino" : "Feminino";
                listaRetorno[i][4] = dateFormat.format(pessoa.getDtNascimento());
                listaRetorno[i][5] = enderecoDTO.getLogradouro();
                listaRetorno[i][6] = enderecoDTO.getCep().toString();
                listaRetorno[i][7] = enderecoDTO.getUfDTO().getDescricao();
                listaRetorno[i][8] = "Deletar";
                listaRetorno[i][9] = "Editar";
            }

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }

        return listaRetorno;
    }

    public boolean validaNome(String nome) throws ValidacaoException {

        boolean ehValido = true;

        if (nome == null || nome.equals("")) {

            ehValido = false;
            throw new ValidacaoException("Campo nome é obrigatório!");

        } else if (nome.length() > 45) {

            ehValido = false;
            throw new ValidacaoException("Campo nome suporta somente 45 caracteres");
        }

        return ehValido;
    }

    public boolean validaCpf (String cpf) throws ValidacaoException {

        boolean ehValido = true;

        if (cpf == null || cpf.equals("")) {

            ehValido = false;
            throw new ValidacaoException("Campo CPF é obrigatório!");

        } else if (cpf.length() != 11) {

            ehValido = false;
            throw new ValidacaoException("CPF deve conter 11 digitos");
        } else {

            char[] digitos = cpf.toCharArray();

            for (char digito : digitos) {

               if (!Character.isDigit(digito)) {

                   ehValido = false;
                   throw new ValidacaoException("Campo CPF é somente números");
               }
            }
        }

        return ehValido;
    }

    public boolean validaEndereco (EnderecoDTO enderecoDTO) throws ValidacaoException {

        boolean ehValido = true;

        if (enderecoDTO.getLogradouro() == null || enderecoDTO.getLogradouro().equals("")) {

            ehValido = false;
            throw new ValidacaoException("Campo logradouro é obrigatório!");

        } else if (enderecoDTO.getBairro() == null || enderecoDTO.getBairro().equals("")) {

            ehValido = false;
            throw new ValidacaoException("Campo bairro é obrigatório!");

        } if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero().equals(0)) {

            ehValido = false;
            throw new ValidacaoException("Campo número é obrigatório!");

        } if (enderecoDTO.getLogradouro() == null || enderecoDTO.getLogradouro().equals("")) {

            ehValido = false;
            throw new ValidacaoException("Campo endereço é obrigatório!");
        }

        return ehValido;
    }

    public boolean validaDtNascimento (String dtNascimento) throws ValidacaoException {

        boolean ehValido = true;

        if (dtNascimento == null || dtNascimento.equals("")) {

            ehValido = false;
            throw new ValidacaoException("Campo data de nascimento é obrigatório!");

        } else {

            ehValido = false;

            try {

                dateFormat.parse(dtNascimento);

            } catch (ParseException e) {

                throw new ValidacaoException("Formato inválido de data");
            }
        }

        return ehValido;
    }

    public String[][] listaConsulta(String nome, Long cpf, char sexo, String orderBy) throws NegocioException {

        int numColumns = 5;
        String[][] listaRetorno = null;

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();

            List<PessoaDTO> lista = pessoaDAO.filtraPessoa(nome, cpf, String.valueOf(sexo), orderBy);

            listaRetorno = new String[lista.size()][numColumns];

            for (int i = 0; i < lista.size(); i++) {

                PessoaDTO pessoa = lista.get(i);

                listaRetorno[i][0] = pessoa.getNome();
                listaRetorno[i][1] = pessoa.getCpf().toString();
                listaRetorno[i][3] = pessoa.getSexo() == 'M' ? "Masculino" : "Feminino";
                listaRetorno[i][4] = dateFormat.format(pessoa.getDtNascimento());
            }

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }

        return listaRetorno;
    }

    public void removePessoa(Integer idPessoa, Integer idEndereco) throws NegocioException {

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();

            pessoaDAO.deletar(idPessoa);
            pessoaDAO.deletarEndereco(idEndereco);

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public void removeTudo() throws NegocioException {

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();

            pessoaDAO.deletarTudo();

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public PessoaDTO buscaPorId(Integer idPessoa) throws NegocioException {

        PessoaDTO pessoaDTO = null;

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();

            pessoaDTO = pessoaDAO.buscarPorID(idPessoa);

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }

        return pessoaDTO;
    }

    public void atualizar(PessoaDTO pessoaDTO) throws NegocioException {

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();

            pessoaDAO.atualizar(pessoaDTO);

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
