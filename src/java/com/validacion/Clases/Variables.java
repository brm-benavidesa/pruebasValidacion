/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validacion.Clases;

/**
 *
 * @author AndresV
 */
public class Variables {
  private double valor;
  private String tipo;

  public Variables(){
     
  }
  
  public String guardarVariable(double valor, String Variables){
    try{
      this.setValor(valor);
      this.tipo=(Variables);
      return "registroOK";
      
    }catch(NumberFormatException ex){
      return ex.getMessage();
    }
  }
  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  
  
  
}
