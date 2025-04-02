package com.examenJava.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.examenJava.config.HexaSingleton;

public class ConnMySql implements ConnectionDb {
    @Override
    public Connection getConexion() throws SQLException {
        HexaSingleton config = HexaSingleton.INSTANCIA;
        String url = config.get("db.url");
        String usuario = config.get("db.user");
        String contrasena = config.get("db.contrasena");

        return DriverManager.getConnection(url, usuario, contrasena);
    }

}
