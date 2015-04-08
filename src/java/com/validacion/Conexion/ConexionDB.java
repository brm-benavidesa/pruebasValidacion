/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validacion.Conexion;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author open12
 */
public class ConexionDB {

  private static MysqlDataSource ds = null;
  private static ConexionDB conexion = null;

  static {
    conexion = new ConexionDB();
  }

  private ConexionDB() {
    ds = new MysqlDataSource();
    ds.setServerName("127.0.0.1");
    ds.setUser("root");
    ds.setPassword("");
    ds.setDatabaseName("sistemavalidacion");
  }

  public static Connection obtenerConexion() throws SQLException {
    return conexion.ds.getConnection();
  }

}
