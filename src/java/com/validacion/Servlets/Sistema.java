/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validacion.Servlets;


import com.validacion.Clases.Calculos;
import com.validacion.Clases.Empleado;
import com.validacion.Clases.Variables;
import com.validacion.DAO.EmpleadoDAO;
import com.validacion.DAO.VariablesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.tribes.util.Logs;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author AndresV
 */

@WebServlet(name = "Sistema", urlPatterns = {"/Sistema"})
public class Sistema extends HttpServlet {
  private int respuesta;
  private String aprobado;
  private double cuotas;
  private static final String CONTENT_TYPE_JSON = "application/json; charset=windows-1252";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
 /* protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet respuesta</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Su Puntaje es de: " + this.respuesta +  " Puntos</h1>");
      out.println("<h1>" + this.aprobado +  "</h1>");
      out.println("<h1>El valor mensual de sus cuotas es: " + this.cuotas +  "</h1>");
      out.println("</body>");
      out.println("</html>");
    } finally {
      out.close();
    }
  }*/

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
   // processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    JSONObject json = new JSONObject();
      response.setContentType(CONTENT_TYPE_JSON);
      PrintWriter out = response.getWriter();
      String empelado[] = null;
      int respuestaPuntos;
      long valorTotal;
      double totalCuotas;
      totalCuotas=0;
      valorTotal=0;
      respuestaPuntos=0;
      //empelado[0]="";
     // System.out.print(json);

    if ((request.getParameter("consultaEmpleado")!=null)) {
      String variablesSistema []=null;
      
      try {
        VariablesDAO varSistema = new VariablesDAO();
        variablesSistema = varSistema.consultarVariables();
      } catch (SQLException ex) {
        Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
        EmpleadoDAO empleadoConsulta = new EmpleadoDAO();
        empelado = empleadoConsulta.consultarEmpleado(Long.parseLong(request.getParameter("consultaEmpleado")));
        this.aprobado = "ok";
      } catch (SQLException ex) {
        this.aprobado = "Error";
        Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
      }
      if ((request.getParameter("estadoCivil")!=null)) {
        boolean casa;
        casa = request.getParameter("casaPropia").equals("true");
        Calculos nuevaConsulta = new Calculos();
        respuestaPuntos = nuevaConsulta.puntosEmpleado(request.getParameter("estadoCivil"), Integer.parseInt(request.getParameter("estrato")), empelado[0], Integer.parseInt(request.getParameter("numHijos")), casa);
        valorTotal = nuevaConsulta.topeMaximo(empelado[1],Long.parseLong(variablesSistema[0]));
      }
      if ((request.getParameter("valorSolicitar")!=null)) {
        int catidadMeses;
        double valorMaxPrestamo;
        valorMaxPrestamo = Double.parseDouble(request.getParameter("valorMaxPrestamo"));
        catidadMeses = (Integer.parseInt(request.getParameter("catidadMeses")))*12;
        Calculos nuevaConsulta = new Calculos();
        totalCuotas = nuevaConsulta.cuotas(Double.parseDouble(variablesSistema[1]), Long.parseLong(request.getParameter("valorSolicitar")), catidadMeses,valorMaxPrestamo);
      }
      json.put("respuesta", empelado[0]);
      json.put("puntos",respuestaPuntos);
      json.put("valorTotal",valorTotal);
      json.put("totalCuotas",totalCuotas);
      out.print(json);
      out.close();
    } else {
        if(request.getParameter("usuario")!=null){
          Calculos validar = new Calculos();
          boolean respuestaMsg;
          respuestaMsg = validar.logIn(request.getParameter("usuario"),request.getParameter("password"));
          if(respuestaMsg){
            json.put("respuesta","ok");
          }else{
            json.put("respuesta","mal");
          }
          out.print(json);
          out.close();
      }
      if ((request.getParameter("SMMLV")!=null)) {
        String variable;
        double SMMLV;
        double interes;
        try{
          SMMLV = Double.parseDouble(request.getParameter("SMMLV"));
          interes = Double.parseDouble(request.getParameter("interes"));
          Variables objVariable = new Variables();
          objVariable.guardarVariable(SMMLV,interes);
          try {
            VariablesDAO varReg = new VariablesDAO();
            variable = varReg.guardar(objVariable);
            json.put("respuesta",variable);
            out.print(json);
            out.close();
          } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
          }
        }catch(NumberFormatException ex){
          json.put("respuesta",ex.getMessage());
          out.print(json);
          out.close();
        }
    }else{
        Empleado nuevoEmpleado = new Empleado();
        int cedula;
        String nombre;
        String cargo;
        double sueldo;
        String fechaIngreso;
        cedula = Integer.parseInt(request.getParameter("id"));
        nombre = request.getParameter("nombre");
        cargo = request.getParameter("tipoCargo");
        sueldo = Double.parseDouble(request.getParameter("sueldo"));
        fechaIngreso = request.getParameter("fechaIngreso");
        nuevoEmpleado.guardarEmpleado(fechaIngreso, cedula, nombre, sueldo, cargo);
        String guardaEmpleado;
        try {
          EmpleadoDAO guardarEmpleado = new EmpleadoDAO();
          //guardarEmpleado.guardar(nuevoEmpleado);
          if (guardarEmpleado.guardar(nuevoEmpleado)) {
            guardaEmpleado = "ok";
          } else {
            guardaEmpleado = "error";
          }
          //this.respuesta = guardarEmpleado.getError();
        } catch (SQLException ex) {
          guardaEmpleado="fatalError";
          Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
          json.put("respuesta",guardaEmpleado);
          out.print(json);
          out.close();
      }
    }
   // processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
