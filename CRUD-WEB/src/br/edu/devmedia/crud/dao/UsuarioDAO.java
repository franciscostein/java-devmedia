package br.edu.devmedia.crud.dao;

import br.edu.devmedia.crud.dto.UsuarioDTO;
import br.edu.devmedia.crud.exception.PersistenciaException;
import br.edu.devmedia.crud.util.ConexaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarUsuario(UsuarioDTO usuario) throws PersistenciaException {

        try {

            Connection conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM TB_USUARIO ");
            sql.append("WHERE USUARIO = ? AND SENHA = ?");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (ClassNotFoundException | SQLException e) {

            throw new PersistenciaException(e);
        }
    }
}
