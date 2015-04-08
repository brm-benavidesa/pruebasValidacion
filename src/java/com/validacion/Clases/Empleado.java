/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validacion.Clases;

import java.sql.Date;

/**
 *
 * @author AndresV
 */
public class Empleado {
  private String fechaIngreso;
  private int id;
  private String nombre;
  private double sueldo;
  private String tipoCargo;

  public Empleado() {}
  
  public String guardarEmpleado(String fechaIngreso, int id, String nombre,  double sueldo, String tipoCargo) {
    if(fechaIngreso.trim().length()!=0 && id!=0 && nombre.trim().length()!=0 && sueldo>0 && tipoCargo.trim().length()!=0){
      if(tipoCargo.equals("A") || tipoCargo.equals("O") || tipoCargo.equals("D") ){
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.tipoCargo = tipoCargo;
      }else{
        return "Error en tipo de cargo";
      }
      return "registroOk";
    }else{
      return "datos incompletos";
    }
    
  }

  public String getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(String fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getSueldo() {
    return sueldo;
  }

  public void setSueldo(double sueldo) {
    this.sueldo = sueldo;
  }

  public String getTipoCargo() {
    return tipoCargo;
  }

  public void setTipoCargo(String tipoCargo) {
    this.tipoCargo = tipoCargo;
  }
    
}
