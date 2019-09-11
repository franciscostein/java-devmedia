package br.edu.devmedia.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexaoUtil {

    private static ResourceBundle configDB = ResourceBundle.getBundle(Constantes.CONEXA_BD_PROPERTIES);

    public static Connection getConexao() throws ClassNotFoundException, SQLException {

        Class.forName(configDB.getString(Constantes.CONEXA_BD_DRIVER));

        return DriverManager.getConnection(configDB.getString(Constantes.CONEXA_BD_URL),
                configDB.getString(Constantes.CONEXA_BD_USER), configDB.getString(Constantes.CONEXA_BD_PASSWORD));
    }
}
