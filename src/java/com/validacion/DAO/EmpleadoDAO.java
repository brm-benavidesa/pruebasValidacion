/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validacion.DAO;

import com.validacion.Clases.Empleado;
import com.validacion.Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author open12
 */
public class EmpleadoDAO {

  private String error = "";

  private Connection conn = null;
  private static String guardarEmpleados = "INSERT INTO empleado (id, nombre,tipo_de_cargo, sueldo,fecha_ingreso) VALUES (?,?,?,?,?)";
  private static String consultaEmpleados = "SELECT * FROM empleado WHERE id = ?";
  private static String todosLosEmpleados = "SELECT * FROM empleado ORDER BY id";
  

  public EmpleadoDAO() throws SQLException {
    this.conn = ConexionDB.obtenerConexion();
  }
 
  public boolean guardar(Empleado guardarEmpleado) {
    this.error = "";
    boolean resultado = true;
    try {
      PreparedStatement ps = conn.prepareStatement(guardarEmpleados);
      ps.setInt(1, guardarEmpleado.getId());
      ps.setString(2, guardarEmpleado.getNombre());
      ps.setString(3, guardarEmpleado.getTipoCargo());
      ps.setDouble(4, guardarEmpleado.getSueldo());
      ps.setString(5, guardarEmpleado.getFechaIngreso());
      if (ps.executeUpdate() == 0) {
        resultado = true;
      }
    } catch (SQLException ex) {
      this.error = "ERROR DE CREACION "+guardarEmpleado.getNombre() ;
      Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
      resultado = false;
    }

    return resultado;
  }
  public String[] consultarEmpleado(long id){
    String[] mensajeDevuelto = new String[9];
    int empleado;
    empleado = 0;
    String fechaIngreso;
    fechaIngreso = "";
    try {
      PreparedStatement ps = conn.prepareStatement(consultaEmpleados);
      ps.setLong(1, id);
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        fechaIngreso = resultado.getString("fecha_ingreso");
        mensajeDevuelto[0] = resultado.getString("fecha_ingreso");
        mensajeDevuelto[1] = resultado.getString("tipo_de_cargo");
        mensajeDevuelto[2] = resultado.getString("nombre");
        mensajeDevuelto[3] = resultado.getString("sueldo");
        empleado++;
     } 
    } catch (SQLException ex) {
      Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    if(empleado == 1){
      return mensajeDevuelto;
    }else{
      mensajeDevuelto[0]="Error";
      return mensajeDevuelto;   
    }
        
  }
  public ArrayList<ArrayList<String>> todosLosEmpleados(){
    ArrayList<ArrayList<String>> allEmpleados = new ArrayList<ArrayList<String>>();
    int empleado;
    empleado = 0;
    String[][] mensajeDevuelto =  null;
    try {
      PreparedStatement ps = conn.prepareStatement(todosLosEmpleados);
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
            allEmpleados.add(new ArrayList<String>());

        allEmpleados.get(empleado).add(resultado.getString("id"));
        allEmpleados.get(empleado).add(resultado.getString("nombre"));
        allEmpleados.get(empleado).add(resultado.getString("tipo_de_cargo"));
        allEmpleados.get(empleado).add(resultado.getString("sueldo"));
        allEmpleados.get(empleado).add(resultado.getString("fecha_ingreso"));
        empleado++;
     } 
    } catch (SQLException ex) {
      Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
      return allEmpleados;
  }

  public String getError() {
    return error;
  }

}
