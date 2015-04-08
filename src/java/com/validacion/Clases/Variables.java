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
  private double SMMLV;
  private double interes;

  public Variables(){
     
  }
  
  public String guardarVariable(double SMMLV, double interes){
    try{
      this.setSMMLV(SMMLV);
      this.interes=(interes);
      return "registroOK";
      
    }catch(NumberFormatException ex){
      return ex.getMessage();
    }
  }
  public double getSMMLV() {
    return SMMLV;
  }

  public void setSMMLV(double SMMLV) {
    this.SMMLV = SMMLV;
  }

  public double getInteres() {
    return interes;
  }

  public void setInteres(double interes) {
    this.interes = interes;
  }
  
  
  
}
