/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validacion.DAO;

import com.validacion.Clases.Empleado;
import com.validacion.Clases.Variables;
import com.validacion.Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.jni.OS;

/**
 *
 * @author AndresV
 */
public class TopeDAO {

  private String error = "";
  private Connection conn = null;
  private static String guardarVariable = "INSERT INTO tope_maximo (id,tipo_de_cargo,cantidad_de_salarios) VALUES (null,?,?)";
  private static String consultaVariable = "SELECT * FROM variable WHERE id IN (SELECT MAX(id) FROM variable GROUP BY tipo) ORDER BY tipo DESC";
  private static String topeXEmpleado= "SELECT cantidad_de_salarios FROM tope_maximo WHERE tipo_de_cargo = ? ORDER BY id DESC LIMIT 1";

  public TopeDAO() throws SQLException {
    this.conn = ConexionDB.obtenerConexion();
  }

  public String guardar(Variables variable) {
    this.error = "";
    boolean resultado = true;
    try {
      PreparedStatement ps = conn.prepareStatement(guardarVariable);
      ps.setString(1, variable.getTipo());
      ps.setDouble(2, variable.getValor());
      if (ps.executeUpdate() == 0) {
        resultado = true;
      }
      this.error ="ok";
    } catch (SQLException ex) {
     // this.error = "ERROR DE CREACION ";
      //System.out.println(ex.getMessage());
      this.error = ex.getMessage();
      Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
      resultado = false;
    }

    return  this.error;
  }

  public String[] consultarVariables(){
    String[] mensajeDevuelto = new String[2];
    int empleado;
    empleado = 0;

    try {
      PreparedStatement ps = conn.prepareStatement(consultaVariable);
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        mensajeDevuelto[empleado] = resultado.getString("valor");
        empleado++;
      }
    } catch (SQLException ex) {
      Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (empleado >= 1) {
      return mensajeDevuelto;
    } else {
      mensajeDevuelto[0] = "Error";
      return mensajeDevuelto;
    }

  }
  public int topeXCargo(String cargo){
    int cantSalarios;
    cantSalarios = 0;
    try {
      PreparedStatement ps = conn.prepareStatement(topeXEmpleado);
      ps.setString(1, cargo);
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        cantSalarios =  Integer.parseInt(resultado.getString("cantidad_de_salarios"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
      return cantSalarios;
    
  }

  public String getError() {
    return error;
  }

}
