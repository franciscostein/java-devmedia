package br.edu.devmedia.crud.dao;

import br.edu.devmedia.crud.dto.CidadeDTO;
import br.edu.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.edu.devmedia.crud.dto.UfDTO;
import br.edu.devmedia.crud.exception.PersistenciaException;
import br.edu.devmedia.crud.util.ConexaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {

    public List<UfDTO> listarUfs() throws PersistenciaException {

        List<UfDTO> lista = new ArrayList<>();

        try {

            Connection conexao = ConexaoUtil.getConexao();

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
        }

        return lista;
    }

    public List<CidadeDTO> consultarCidadesPorEstado(Integer idEstado) throws PersistenciaException {

        List<CidadeDTO> listaCidades = new ArrayList<>();

        try {

            Connection conexao = ConexaoUtil.getConexao();

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
        }

        return listaCidades;
    }

    public List<PreferenciaMusicalDTO> listarPreferencias() {

        List<PreferenciaMusicalDTO> listaPreferencias = new ArrayList<>();

        try {

            Connection conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM TB_PREFENCIA");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                PreferenciaMusicalDTO preferencia = new PreferenciaMusicalDTO();
                preferencia.setIdPreferencia(resultSet.getInt(1));
                preferencia.setDescricao(resultSet.getString(2));

                listaPreferencias.add(preferencia);
            }

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }

        return listaPreferencias;
    }
}
