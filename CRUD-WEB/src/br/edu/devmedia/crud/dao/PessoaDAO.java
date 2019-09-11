package br.edu.devmedia.crud.dao;

import br.edu.devmedia.crud.dto.*;
import br.edu.devmedia.crud.exception.PersistenciaException;
import br.edu.devmedia.crud.util.ConexaoUtil;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public List<UfDTO> listarUfs() throws PersistenciaException {

        List<UfDTO> lista = new ArrayList<>();
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM TB_UF");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                UfDTO ufDTO = new UfDTO();
                ufDTO.setIdUf(resultSet.getInt(1));
                ufDTO.setSigla(resultSet.getString(2));
                ufDTO.setDescricao(resultSet.getString(3));

                lista.add(ufDTO);
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return lista;
    }

    public List<CidadeDTO> consultarCidadesPorEstado(Integer idEstado) throws PersistenciaException {

        List<CidadeDTO> listaCidades = new ArrayList<>();
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM TB_CIDADE ");
            sql.append("WHERE COD_ESTADO = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setInt(1, idEstado);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                CidadeDTO cidade = new CidadeDTO();
                cidade.setIdCidade(resultSet.getInt("id_cidade"));
                cidade.setDescricao(resultSet.getString("descricao"));

                UfDTO uf = new UfDTO();
                uf.setIdUf(resultSet.getInt("cod_estado"));

                cidade.setUf(uf);

                listaCidades.add(cidade);
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return listaCidades;
    }

    public List<PreferenciaMusicalDTO> listarPreferencias() throws PersistenciaException {

        List<PreferenciaMusicalDTO> listaPreferencias = new ArrayList<>();
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM TB_PREFERENCIA");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                PreferenciaMusicalDTO preferencia = new PreferenciaMusicalDTO();
                preferencia.setIdPreferencia(resultSet.getInt(1));
                preferencia.setDescricao(resultSet.getString(2));

                listaPreferencias.add(preferencia);
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return listaPreferencias;
    }

    private void cadastrarPreferencias(List<PreferenciaMusicalDTO> preferencias, Integer codPessoa) throws PersistenciaException {

        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TB_PREFERENCIA_PESSOA ");
            sql.append("VALUES(?, ?)");

            for (PreferenciaMusicalDTO preferencia : preferencias) {

                PreparedStatement statement = conexao.prepareStatement(sql.toString());
                statement.setInt(1, preferencia.getIdPreferencia());
                statement.setInt(2, codPessoa);

                statement.execute();
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public void cadastrarPessoa(PessoaDTO pessoa) throws PersistenciaException {

        Integer idGerado = null;
        Connection conexao = null;

        try {

            Integer codEndereco = cadastrarEndereco(pessoa.getEndereco());

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TB_PESSOA(NOME, CPF, DT_NASC, SEXO, MINI_BIO, COD_ENDERECO) ");
            sql.append("VALUES(?, ?, ?, ?, ?, ?)");

            PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCpf());
            Date dtNasc = new Date(dateFormat.parse(pessoa.getDtNasc()).getTime());
            statement.setDate(3, dtNasc);
            statement.setString(4, String.valueOf(pessoa.getSexo()));
            statement.setString(5, pessoa.getMiniBio());
            statement.setInt(6, codEndereco);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.first()) {

                idGerado = resultSet.getInt(1);
            }

            cadastrarPreferencias(pessoa.getPreferencias(), idGerado);

        } catch (ClassNotFoundException | SQLException | ParseException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    private Integer cadastrarEndereco(EnderecoDTO endereco) throws PersistenciaException {

        Integer idGerado = null;
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TB_ENDERECO(LOGRADOURO, COD_CIDADE) ");
            sql.append("VALUES(?, ?)");

            PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, endereco.getLogradouro());
            statement.setInt(2, endereco.getCidade().getIdCidade());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.first()) {

                idGerado = resultSet.getInt(1);
            }

            return idGerado;

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public void atualizarPessoa(PessoaDTO pessoa) throws PersistenciaException {

        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TB_PESSOA ");
            sql.append("SET NOME = ?, CPF = ?, DT_NASC = ?, SEXO = ?, MINI_BIO = ? ");
            sql.append("WHERE ID_PESSOA = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCpf());

            Date dtNasc = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(pessoa.getDtNasc());

            statement.setDate(3, new Date(dtNasc.getTime()));
            statement.setString(4, String.valueOf(pessoa.getSexo()));
            statement.setString(5, pessoa.getMiniBio());
            statement.setInt(6, pessoa.getIdPessoa());

            statement.executeUpdate();

            removerPreferencias(pessoa.getIdPessoa());
            cadastrarPreferencias(pessoa.getPreferencias(), pessoa.getIdPessoa());

            atualizarEndereco(pessoa.getEndereco());

        } catch (ClassNotFoundException | SQLException | ParseException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    private void atualizarEndereco(EnderecoDTO endereco) throws PersistenciaException {

        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TB_ENDERECO SET LOGRADOURO = ?, COD_CIDADE = ? ");
            sql.append("WHERE ID_ENDERECO = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setString(1, endereco.getLogradouro());
            statement.setInt(2, endereco.getCidade().getIdCidade());
            statement.setInt(3, endereco.getIdEndereco());

            statement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public List<PessoaDTO> listarPessoas() throws PersistenciaException {

        List<PessoaDTO> listaPessoas = new ArrayList<>();
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT PE.ID_PESSOA, PE.NOME, PE.CPF, PE.DT_NASC, PE.SEXO,");
            sql.append("    EN.LOGRADOURO, CID.DESCRICAO desc_cid, UF.DESCRICAO desc_uf ");
            sql.append("FROM TB_PESSOA PE ");
            sql.append("INNER JOIN TB_ENDERECO EN");
            sql.append("    ON PE.COD_ENDERECO = EN.ID_ENDERECO ");
            sql.append("INNER JOIN TB_CIDADE CID");
            sql.append("    ON EN.COD_CIDADE = CID.ID_CIDADE ");
            sql.append("INNER JOIN TB_UF UF");
            sql.append("    ON CID.COD_ESTADO = UF.ID_UF ");
            sql.append("ORDER BY PE.ID_PESSOA");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                PessoaDTO pessoa = new PessoaDTO();
                pessoa.setIdPessoa(resultSet.getInt("id_pessoa"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setCpf(resultSet.getString("cpf"));
                pessoa.setSexo(resultSet.getString("sexo").charAt(0));
                pessoa.setDtNasc(dateFormat.format(resultSet.getDate("dt_nasc")));

                EnderecoDTO endereco = new EnderecoDTO();
                endereco.setLogradouro(resultSet.getString("logradouro"));

                CidadeDTO cidade = new CidadeDTO();
                cidade.setDescricao(resultSet.getString("desc_cid"));

                UfDTO uf = new UfDTO();
                uf.setDescricao(resultSet.getString("desc_uf"));

                endereco.setCidade(cidade);
                cidade.setUf(uf);
                pessoa.setEndereco(endereco);

                pessoa.setPreferencias(consultarPreferencias(pessoa.getIdPessoa()));

                listaPessoas.add(pessoa);
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return listaPessoas;
    }

    public PessoaDTO consultarPessoaPorId(Integer idPessoa) throws PersistenciaException {

        PessoaDTO pessoa = null;
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT PE.ID_PESSOA, PE.NOME, PE.CPF, PE.DT_NASC, PE.SEXO, PE.MINI_BIO, EN.ID_ENDERECO,");
            sql.append("    EN.LOGRADOURO, CID.ID_CIDADE, CID.DESCRICAO desc_cid, UF.ID_UF, UF.DESCRICAO desc_uf ");
            sql.append("FROM TB_PESSOA PE ");
            sql.append("INNER JOIN TB_ENDERECO EN");
            sql.append("    ON PE.COD_ENDERECO = EN.ID_ENDERECO ");
            sql.append("INNER JOIN TB_CIDADE CID");
            sql.append("    ON EN.COD_CIDADE = CID.ID_CIDADE ");
            sql.append("INNER JOIN TB_UF UF");
            sql.append("    ON CID.COD_ESTADO = UF.ID_UF ");
            sql.append("WHERE ID_PESSOA = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setInt(1, idPessoa);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {

                pessoa = new PessoaDTO();
                pessoa.setIdPessoa(resultSet.getInt("id_pessoa"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setCpf(resultSet.getString("cpf"));
                pessoa.setMiniBio(resultSet.getString("mini_bio"));
                pessoa.setSexo(resultSet.getString("sexo").charAt(0));
                pessoa.setDtNasc(dateFormat.format(resultSet.getDate("dt_nasc")));

                EnderecoDTO endereco = new EnderecoDTO();
                endereco.setIdEndereco(resultSet.getInt("id_endereco"));
                endereco.setLogradouro(resultSet.getString("logradouro"));

                CidadeDTO cidade = new CidadeDTO();
                cidade.setIdCidade(resultSet.getInt("id_cidade"));
                cidade.setDescricao(resultSet.getString("desc_cid"));

                UfDTO uf = new UfDTO();
                uf.setIdUf(resultSet.getInt("id_uf"));
                uf.setDescricao(resultSet.getString("desc_uf"));

                endereco.setCidade(cidade);
                cidade.setUf(uf);
                pessoa.setEndereco(endereco);

                pessoa.setPreferencias(consultarPreferencias(pessoa.getIdPessoa()));
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return pessoa;
    }

    private List<PreferenciaMusicalDTO> consultarPreferencias(Integer idPessoa) throws PersistenciaException {

        List<PreferenciaMusicalDTO> listaPrefencias = new ArrayList<>();
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT PRE.ID_PREFERENCIA, PRE.DESCRICAO FROM TB_PREFERENCIA PRE ");
            sql.append("INNER JOIN TB_PREFERENCIA_PESSOA PREPES");
            sql.append("    ON PRE.ID_PREFERENCIA = PREPES.COD_PREFERENCIA ");
            sql.append("INNER JOIN TB_PESSOA PES");
            sql.append("    ON PES.ID_PESSOA = PREPES.COD_PESSOA ");
            sql.append("WHERE PES.ID_PESSOA = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setInt(1, idPessoa);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                PreferenciaMusicalDTO preferenciaMusical = new PreferenciaMusicalDTO();
                preferenciaMusical.setIdPreferencia(resultSet.getInt("id_preferencia"));
                preferenciaMusical.setDescricao(resultSet.getString("descricao"));

                listaPrefencias.add(preferenciaMusical);
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return listaPrefencias;
    }

    public void removerPessoa(Integer idPessoa) throws PersistenciaException {

        Connection conexao = null;

        try {

            PessoaDTO pessoa = consultarPessoaPorId(idPessoa);

            if (pessoa.getPreferencias() != null && !pessoa.getPreferencias().isEmpty()) {

                removerPreferencias(pessoa.getIdPessoa());
            }

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TB_PESSOA WHERE ID_PESSOA = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setInt(1, pessoa.getIdPessoa());

            statement.execute();

            if (pessoa.getEndereco() != null && pessoa.getEndereco().getIdEndereco() != null) {

                removerEndereco(pessoa.getEndereco().getIdEndereco());
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    private void removerEndereco(Integer idEndereco) throws PersistenciaException {

        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TB_ENDERECO WHERE ID_ENDERECO = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setInt(1, idEndereco);

            statement.execute();

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    private void removerPreferencias(Integer idPessoa) throws PersistenciaException {

        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TB_PREFERENCIA_PESSOA WHERE COD_PESSOA = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setInt(1, idPessoa);

            statement.execute();

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public List<PessoaDTO> filtrar(PessoaDTO pessoa) throws PersistenciaException {

        Connection conexao = null;
        List<PessoaDTO> listaPessoas = new ArrayList<>();

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT PE.ID_PESSOA, PE.NOME, PE.CPF, PE.DT_NASC, PE.SEXO, PE.MINI_BIO, EN.ID_ENDERECO,");
            sql.append("    EN.LOGRADOURO, CID.ID_CIDADE, CID.DESCRICAO desc_cid, UF.ID_UF, UF.DESCRICAO desc_uf ");
            sql.append("FROM TB_PESSOA PE ");
            sql.append("INNER JOIN TB_ENDERECO EN");
            sql.append("    ON PE.COD_ENDERECO = EN.ID_ENDERECO ");
            sql.append("INNER JOIN TB_CIDADE CID");
            sql.append("    ON EN.COD_CIDADE = CID.ID_CIDADE ");
            sql.append("INNER JOIN TB_UF UF");
            sql.append("    ON CID.COD_ESTADO = UF.ID_UF ");
            sql.append("WHERE 1 = 1");

            if (pessoa.getNome() != null && !"".equals(pessoa.getNome())) {
                sql.append(" AND PE.NOME LIKE ?");
            }

            if (pessoa.getCpf() != null && !"".equals(pessoa.getCpf())) {
                sql.append(" AND PE.CPF LIKE ?");
            }

            if (pessoa.getDtNasc() != null && !"".equals(pessoa.getDtNasc())) {
                sql.append(" AND PE.DT_NASC = ?");
            }

            if (pessoa.getSexo() != 0 && pessoa.getSexo() != ' ') {
                sql.append(" AND PE.SEXO = ?");
            }

            if (!pessoa.getPreferencias().isEmpty()) {

                List<Integer> listaIds = filtrarPrefencencias(pessoa.getPreferencias());

                if (!listaIds.isEmpty()) {

                    String cont = "";
                    for (Integer idPes : listaIds) {
                        cont += idPes;
                        if (listaIds.indexOf(idPes) + 1 != listaIds.size()) {
                            cont += ", ";
                        }
                    }
                    sql.append(" AND PE.ID_PESSOA IN (").append(cont).append(")");
                }
            }

            EnderecoDTO endereco = pessoa.getEndereco();
            Integer idUf = endereco.getCidade().getUf().getIdUf();
            if (idUf != null && idUf != 0) {
                sql.append(" AND UF.ID_UF = ").append(endereco.getCidade().getUf().getIdUf());
            }

            Integer idCidade = endereco.getCidade().getIdCidade();
            if (idCidade != null && idCidade != 0) {
                sql.append(" AND CID.ID_CIDADE = ").append(endereco.getCidade().getIdCidade());
            }

            if (endereco.getLogradouro() != null && !"".equals(endereco.getLogradouro())) {
                sql.append(" AND EN.LOGRADOURO LIKE ? ");
            }

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            int cont = 0;

            if (pessoa.getNome() != null && !"".equals(pessoa.getNome())) {
                statement.setString(++cont, "%" + pessoa.getNome() + "%");
            }

            if (pessoa.getCpf() != null && !"".equals(pessoa.getCpf())) {
                statement.setString(++cont, "%" + pessoa.getCpf() + "%");
            }

            if (pessoa.getDtNasc() != null && !"".equals(pessoa.getDtNasc())) {
                statement.setDate(++cont, new Date(dateFormat.parse(pessoa.getDtNasc()).getTime()));
            }

            if (pessoa.getSexo() != 0 && pessoa.getSexo() != ' ') {
                statement.setString(++cont, String.valueOf(pessoa.getSexo()));
            }

            if (endereco.getLogradouro() != null && !"".equals(endereco.getLogradouro())) {
                statement.setString(++cont, "%" + endereco.getLogradouro() + "%");
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                PessoaDTO pessoaAux = new PessoaDTO();
                pessoaAux.setIdPessoa(resultSet.getInt("id_pessoa"));
                pessoaAux.setNome(resultSet.getString("nome"));
                pessoaAux.setCpf(resultSet.getString("cpf"));
                pessoaAux.setMiniBio(resultSet.getString("mini_bio"));
                pessoaAux.setSexo(resultSet.getString("sexo").charAt(0));
                pessoaAux.setDtNasc(dateFormat.format(resultSet.getDate("dt_nasc")));

                endereco = new EnderecoDTO();
                endereco.setIdEndereco(resultSet.getInt("id_endereco"));
                endereco.setLogradouro(resultSet.getString("logradouro"));

                CidadeDTO cidade = new CidadeDTO();
                cidade.setIdCidade(resultSet.getInt("id_cidade"));
                cidade.setDescricao(resultSet.getString("desc_cid"));

                UfDTO uf = new UfDTO();
                uf.setIdUf(resultSet.getInt("id_uf"));
                uf.setDescricao(resultSet.getString("desc_uf"));

                endereco.setCidade(cidade);
                cidade.setUf(uf);
                pessoaAux.setEndereco(endereco);

                pessoaAux.setPreferencias(consultarPreferencias(pessoaAux.getIdPessoa()));

                listaPessoas.add(pessoaAux);
            }

        } catch (ClassNotFoundException | SQLException | ParseException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return listaPessoas;
    }

    private List<Integer> filtrarPrefencencias(List<PreferenciaMusicalDTO> preferencias) throws PersistenciaException {

        List<Integer> listaIdsPessoas = new ArrayList<>();
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT distinct PES.ID_PESSOA FROM ");
            sql.append("TB_PREFERENCIA PREF INNER JOIN TB_PREFERENCIA_PESSOA TPP");
            sql.append("    ON PREF.ID_PREFERENCIA = TPP.COD_PREFERENCIA ");
            sql.append("INNER JOIN TB_PESSOA PES");
            sql.append("    ON PES.ID_PESSOA = TPP.COD_PESSOA ");

            String cont = "";

            for (PreferenciaMusicalDTO p : preferencias) {

                cont += p.getIdPreferencia();

                if (preferencias.indexOf(p) + 1 != preferencias.size()) {

                    cont += ", ";
                }
            }

            sql.append("WHERE PREF.ID_PREFERENCIA IN (");
            sql.append(cont);
            sql.append(")");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                listaIdsPessoas.add(resultSet.getInt(1));
            }

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);

        } finally {

            try {

                conexao.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return listaIdsPessoas;
    }
}