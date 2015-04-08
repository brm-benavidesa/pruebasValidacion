/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validacion.Clases;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author AndresV
 */
public class Calculos {

  public static String getFechaActual() {
    Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
    return formateador.format(ahora);
  }

  public static synchronized java.util.Date deStringToDate(String fecha) {
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaEnviar = null;
    try {
      fechaEnviar = formatoDelTexto.parse(fecha);
      return fechaEnviar;
    } catch (ParseException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public int diferenciaFechas(String fec1, String fec2, int valor) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    int retorno = 0;
    java.util.Date date1 = null;
    java.util.Date date2 = null;
    try {
      Calendar cal1 = null;
      date1 = df.parse(fec1);
      cal1 = Calendar.getInstance();

      Calendar cal2 = null;
      date2 = df.parse(fec2);
      cal2 = Calendar.getInstance();

// different date might have different offset 
      cal1.setTime(date1);
      long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);

      cal2.setTime(date2);
      long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);

// Use integer calculation, truncate the decimals 
      int hr1 = (int) (ldate1 / 3600000); //60*60*1000 
      int hr2 = (int) (ldate2 / 3600000);

      int days1 = (int) hr1 / 24;
      int days2 = (int) hr2 / 24;

      int dateDiff = days2 - days1;
      int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
      int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

      if (valor == 1) {
        if (dateDiff < 0) {
          dateDiff = dateDiff * (-1);
        }
        retorno = dateDiff;
      } else if (valor == 2) {
        if (monthDiff < 0) {
          monthDiff = monthDiff * (-1);
        }
        retorno = monthDiff;
      } else if (valor == 3) {
        if (yearDiff < 0) {
          yearDiff = yearDiff * (-1);
        }
        retorno = yearDiff;
      }
    } catch (ParseException pe) {
      pe.printStackTrace();
    }
    return retorno;
  }

  public int puntosEmpleado(String estadoCivil, int estrato, String fechaIngreso, int numHijos, boolean casaPropia) {
    int puntos;
    puntos = 0;
    if(estrato>0 && estrato>=0 ){
      if(estadoCivil.equals("C") || estadoCivil.equals("S")){
        if (estadoCivil.equals("C") && numHijos == 0) {
          puntos = puntos + 50;
        }
        if (estadoCivil.equals("C") && numHijos > 0 && numHijos <= 2) {
          puntos = puntos + 60;
        }
        if (estadoCivil.equals("C") && numHijos > 2) {
          puntos = puntos + 70;
        }
        if (estadoCivil.equals("S") && numHijos >= 1) {
          puntos = puntos + 30;
        }
        if (!casaPropia) {
          puntos = puntos + 50;
        }
        switch (estrato) {
          case 1:
          case 2:
            puntos = puntos + 40;
            break;
          case 3:
          case 4:
            puntos = puntos + 35;
            break;
          case 5:
          case 6:
            puntos = puntos + 30;
            break;
        }
        int anios = (diferenciaFechas((getFechaActual()), (fechaIngreso), 1)) / 365;
        int puntosxAnio;
        puntosxAnio = 0;
        for (int i = 1; i <= anios; i++) {
          if (puntosxAnio <= 75) {
            puntos = puntos + 5;
            puntosxAnio = puntosxAnio +5 ;
          }
        }
      }else{
        puntos = -1;
      }
    }else{
      puntos = -1;
    }
    return puntos;
  }

  public long topeMaximo(String tipoEmpeado, long SMMLV) {
    long totalPrestamo;
    if(SMMLV>1){
      if (tipoEmpeado.equals("A")) {
        totalPrestamo = (SMMLV * 100);
      }
      else if (tipoEmpeado.equals("O")) {
        totalPrestamo = (SMMLV * 110);
      }
      else if (tipoEmpeado.equals("D")) {
        totalPrestamo = (SMMLV * 90);
      }else{
        totalPrestamo = (-1);
      }
    }else{
      totalPrestamo = (-1);
    }
    return totalPrestamo;
  }

  public double cuotas(double tasaAnual,long cantidadPrestamo,int catidadMeses,double valorMaxPrestamo) {
    //tasaAnual = 0.7;
    double cuotas;
      //cantidadPrestamo = 30000000;
      if(tasaAnual>=0 && cantidadPrestamo>=0 &&(catidadMeses>=60 && catidadMeses<=180)){
        if(cantidadPrestamo<=valorMaxPrestamo){
          double tasaMensual;
          catidadMeses = catidadMeses * (-1);
          //interesMensual =  (Math.pow((1+0.13),(1/12)))-1;
          tasaMensual = (Math.pow((1 + tasaAnual), (0.083333333))) - 1;
          cuotas = (tasaMensual * cantidadPrestamo) / (1 - (Math.pow((1 + tasaMensual), catidadMeses)));
        }else{
          cuotas = -2;
        }
    }else{
      cuotas = -1;
    }
    return cuotas;
  }
  public boolean logIn(String user,String password){
    boolean respuesta;
    respuesta = user.equals("admin") && password.equals("123");
    return respuesta;
  }
}
