<%-- 
    Document   : index
    Created on : 8/03/2015, 03:36:15 PM
    Author     : AndresV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/libs/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/libs/twitter-bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="js/calculos.js"></script>
    <link rel="stylesheet" type="text/css" href="js/libs/twitter-bootstrap/css/bootstrap.css" media="all" />
    <title>Guardar empleado</title>
  </head>
  <body>
    <br />
    <br />
    <form action="Sistema" class="form" method="post" role="form" id="logIn" style="display: block">
      <div class="form-group">
        <div class="col-lg-2">
          <input type="text" class="form-control" name="usuario" id="usuario"
                 placeholder="Usuario">
        </div>
      </div>
      <div class="form-group">
        <div class="col-lg-2">
          <input type="password" class="form-control" name="password" id="password" 
                 placeholder="Contraseña">
        </div>
      </div>
      <div class="col-lg-2 control-label">
        <button type="button"  id="logButton" class="btn btn-success">Ingresar</button>
      </div>
    </form>
    <div class="container-fluid" style="display: none"  id="admin">
      <div class="col-sm-5 col-md-5">
        <form action="Sistema" class="form-horizontal" method="post" role="form" id="guardarEmpleado" >
          <div class="form-group">
            <label for="id" class="col-lg-2 control-label">id del  Empleado</label>
            <div class="col-lg-5">
              <input type="id" class="form-control" name="id" id="id"
                     placeholder="id del  Empleado">
            </div>
          </div>
          <div class="form-group">
            <label for="nombre" class="col-lg-2 control-label">Nombre</label>
            <div class="col-lg-5">
              <input type="text" class="form-control" name="nombre" id="nombre" 
                     placeholder="Nombre Completo del empleado">
            </div>
          </div>
          <div class="form-group">
            <label for="tipoCargo" class="col-lg-2 control-label">Tipo de Cargo</label>
            <div class="col-lg-5">
              <select class="form-control" name="tipoCargo" id="tipoCargo">
                <option value="A">Administrativo </option>
                <option value="O">Operativo</option>
                <option value="D">Directivo </option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="sueldo" class="col-lg-2 control-label">Sueldo</label>
            <div class="col-lg-5">
              <input type="text" class="form-control" name="sueldo" id="sueldo" 
                     placeholder="Sueldo del empleado">
            </div>
          </div>
          <div class="form-group">
            <label for="fechaIngreso" class="col-lg-2 control-label">Fecha de Ingreso</label>
            <div class="col-lg-5">
              <input type="date" class="form-control" name="fechaIngreso" id="fechaIngreso" 
                     placeholder="Fecha de ingreso del empleado">
            </div>
          </div>
          <div class="col-lg-2 control-label">
            <button type="button" id="guardaEmpleado" class="btn btn-warning">Guardar Empleado</button>
          </div>
          <div class="form-group">
            <br>
            <h3 class="col-lg-4 control-label" style="color: red;display: none" id="empMsg">Empleado Guardado</h3>   
          </div>
        </form>
      </div>
      <div class="col-sm-7 col-md-7">
        <form action="Sistema" class="form-horizontal" method="post" role="form" id="guardarVariables" >
          <div class="form-group">
            <label for="SMMLV" class="col-lg-2 control-label">SMMLV</label>
            <div class="col-lg-4">
              <input type="text" class="form-control" name="SMMLV" id="SMMLV"
                     placeholder="SMMLV">
            </div>
          </div>
          <div class="form-group">
            <label for="interes" class="col-lg-2 control-label">% de Interes</label>
            <div class="col-lg-4">
              <input type="text" class="form-control" name="interes" id="interes" 
                     placeholder="% de Interes">
            </div>
          </div>
          <div class="col-lg-2 control-label">
            <button type="button" id="guardaVariable" class="btn btn-primary">Guardar</button>
          </div><br>
          <div class="form-group">
            <h3 class="col-lg-4 control-label" style="color: red;display: none" id="varMsg">Variables Guardadas</h3>
   
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
