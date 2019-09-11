package br.edu.devmedia.crud.bo;

import br.edu.devmedia.crud.dao.PessoaDAO;
import br.edu.devmedia.crud.dto.CidadeDTO;
import br.edu.devmedia.crud.dto.PessoaDTO;
import br.edu.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.edu.devmedia.crud.dto.UfDTO;
import br.edu.devmedia.crud.exception.NegocioException;
import br.edu.devmedia.crud.exception.PersistenciaException;
import br.edu.devmedia.crud.util.MensagemContantes;
import br.edu.devmedia.crud.validator.CPFValidator;
import br.edu.devmedia.crud.validator.DataValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PessoaBO {

    private PessoaDAO pessoaDAO;

    public PessoaBO() {
        pessoaDAO = new PessoaDAO();
    }

    public boolean validarPessoa(PessoaDTO pessoaDTO) throws NegocioException {

        boolean isValid = true;

        try {
            //Valida campos obrigatórios
            if (pessoaDTO.getNome() == null || "".equals(pessoaDTO.getNome())) {

                throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Nome"));
            }

            Map<String, Object> valores = new HashMap<>();
            valores.put("CPF", pessoaDTO.getCpf());

            if (new CPFValidator().validar(valores)) {

                isValid = true;
            }

            valores = new HashMap<>();
            valores.put("Data Nascimento", pessoaDTO.getDtNasc());

            if (new DataValidator().validar(valores)) {

                isValid = true;
            }

            if (pessoaDTO.getSexo() == ' '){

                throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Sexo"));
            }

            CidadeDTO cidade = pessoaDTO.getEndereco().getCidade();

            if (cidade.getUf().getIdUf() == null || cidade.getUf().getIdUf() == 0) {

                throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "UF"));
            }

            if (cidade.getIdCidade() == null || cidade.getIdCidade() == 0){

                throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Cidade"));
            }

            if (pessoaDTO.getEndereco().getLogradouro() == null || "".equals((pessoaDTO.getEndereco().getLogradouro()))) {

                throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Logradouro"));
            }

            if (!isValid) {

                throw new NegocioException(MensagemContantes.MSG_ERR_PESSOA_DADOS_INVALIDOS);

            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new NegocioException(e);
        }

        return isValid;
    }

    public void cadastrarPessoa(PessoaDTO pessoa) throws NegocioException {

        try {

            pessoaDAO.cadastrarPessoa(pessoa);

        } catch (PersistenciaException e) {

            throw new NegocioException(e);
        }
    }

    public List<PessoaDTO> listarPessoas() throws NegocioException {

        List<PessoaDTO> pessoas = null;

        try {

            pessoas = pessoaDAO.listarPessoas();

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e);
        }

        return pessoas;
    }

    public void removerPessoa(Integer idPessoa) throws NegocioException {

        try {

            pessoaDAO.removerPessoa(idPessoa);

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e);
        }
    }

    public void atualizarPessoa(PessoaDTO pessoa) throws NegocioException {

        try {

            pessoaDAO.atualizarPessoa(pessoa);

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e);
        }
    }

    public PessoaDTO consultarPessoaPorId(Integer idPessoa) throws NegocioException {

        try {

            return pessoaDAO.consultarPessoaPorId(idPessoa);

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e);
        }
    }

    public List<PessoaDTO> filtrar(PessoaDTO pessoa) throws NegocioException {

        try {

            return pessoaDAO.filtrar(pessoa);

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e.getMessage());
        }
    }

    public List<UfDTO> listarUfs() throws NegocioException {

        try {

            return pessoaDAO.listarUfs();

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e.getMessage());
        }
    }

    public List<PreferenciaMusicalDTO> listarPreferencias() throws NegocioException {

        try {

            return pessoaDAO.listarPreferencias();

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e.getMessage());
        }
    }

    public List<CidadeDTO> consultarCidadesPorEstado(Integer idUf) throws NegocioException {

        try {

            return pessoaDAO.consultarCidadesPorEstado(idUf);

        } catch (PersistenciaException e) {

            e.printStackTrace();
            throw new NegocioException(e.getMessage());
        }
    }
}
