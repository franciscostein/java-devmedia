package br.edu.devmedia.jdbc.dao;

import br.edu.devmedia.jdbc.ConexaoUtil;
import br.edu.devmedia.jdbc.dto.UfDTO;
import br.edu.devmedia.jdbc.exception.PersistenciaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UfDAO {

    public List<UfDTO> listaEstados() throws PersistenciaException {

        List<UfDTO> lista = new ArrayList<UfDTO>();

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM TB_UF";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {

                UfDTO ufDTO = new UfDTO();

                ufDTO.setIdUf(resultado.getInt(1));
                ufDTO.setSiglaUf(resultado.getString(2));
                ufDTO.setDescricao(resultado.getString(3));

                lista.add(ufDTO);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return lista;
    }
}
