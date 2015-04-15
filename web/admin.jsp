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
    <script type="text/javascript" src="js/libs/jquery-validate/jquery.validate.js"></script>
    <script type="text/javascript" src="js/validaAdmin.js"></script>
    <script type="text/javascript" src="js/admin.js"></script>
    <link rel="stylesheet" type="text/css" href="js/libs/twitter-bootstrap/css/bootstrap.css" media="all" />
    <title>Administrador</title>
    <style>
      .editar{
        color:green;
        cursor: pointer;
        height: 36px;
        text-align: center;
        padding: 7px;
      }
      .borrar{
        color:red;
        cursor: pointer;
        height: 36px;
        text-align: center;
        padding: 7px;
      }
      .error{
        color: red;
        font-size: 0.8em;
      }
    </style>
  </head>
  <body>
    <br />
    <br />
    <form action="Sistema" class="form" method="post" role="form" id="logIn" style="display: none">
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
    <div class="container-fluid" style="display: block" >
      <div role="tabpanel">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
          <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Guardar Empleado</a></li>
          <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Guardar Variables</a></li>
          <li role="presentation"><a href="#topeMaximo" aria-controls="topeMaximo" role="tab" data-toggle="tab">Guardar Tope Maximo</a></li>
          <li role="presentation" id="tablaEmpleados"><a href="#consulEmple" aria-controls="consulEmple" role="tab" data-toggle="tab">Consultar Empleado</a></li>
        </ul>
        <br>
        <br>
        <div class="tab-content" id="admin">
          <div class="col-sm-5 col-md-5 tab-pane active" role="tabpanel"  id="home">
            <form action="Sistema" class="form-horizontal" method="post" role="form" id="guardarEmpleado" >
              <div class="form-group">
                <label for="id" class="col-lg-2 control-label">id del  Empleado</label>
                <div class="col-lg-5">
                  <input type="text" class="form-control" name="id" id="id"
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
                  <input type="date" class="form-control" name="fechaIngreso"  id="fechaIngreso" 
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
          <div class="col-sm-12 col-md-12 tab-pane" role="tabpanel"  id="profile">
            <div class="row">
              <div class="col-lg-12 col-md-12">
                <h3>Las variables actuales del sistemas son SMMLV:<span id="valSMMLV"></span> Tasa de interes: <span id="valInteres"></span></h3>
              </div>
              <br />
            </div>
            <div class="row">
              <form action="Sistema" class="form-horizontal" method="post" role="form" id="guardarVariables" >
                <div class="form-group">
                  <label for="SMMLV" class="col-lg-2 control-label">SMMLV</label>
                  <div class="col-lg-3">
                    <input type="text" class="form-control" name="SMMLV" id="SMMLV"
                           placeholder="SMMLV">
                  </div>
                </div>
                <div class="form-group">
                  <label for="interes" class="col-lg-2 control-label">Tasa de Interes</label>
                  <div class="col-lg-3">
                    <input type="text" class="form-control" name="interes" id="interes" 
                           placeholder="Tasa de Interes">
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
          <div class="col-sm-7 col-md-7 tab-pane" role="tabpanel"  id="topeMaximo">
            <form action="Sistema" class="form-horizontal" method="post" role="form" id="guardarTope" >
              <div class="form-group">
                <label for="admin" class="col-lg-2 control-label">Administrativo</label>
                <div class="col-lg-4">
                  <input type="text" class="form-control" name="admin" id="admin" placeholder="cantidad de SMMLV">
                </div>
              </div>
              <div class="form-group">
                <label for="opera" class="col-lg-2 control-label">Operativo</label>
                <div class="col-lg-4">
                  <input type="text" class="form-control" name="opera" id="opera" placeholder="cantidad de SMMLV">
                </div>
              </div>
              <div class="form-group">
                <label for="admin" class="col-lg-2 control-label">Directivo</label>
                <div class="col-lg-4">
                  <input type="text" class="form-control" name="direc" id="direc" placeholder="cantidad de SMMLV">
                </div>
              </div>
              <div class="col-lg-2 control-label">
                <button type="button" id="guardaTope" class="btn btn-success">Guardar</button>
              </div><br>
              <div class="form-group">
                <h3 class="col-lg-4 control-label" style="color: red;display: none" id="varMsg">Topes Guardados</h3>

              </div>
            </form>
          </div>
          <div class="row tab-pane"  role="tabpanel" id="consulEmple">
            <div class="col-lg-12 col-md-12">
              <div class="row">
                <div class="col-lg-12 col-md-12">
                  <form class='form-horizontal' role='form' id="formEdit">
                    <div class="form-group">
                      <div class="col-lg-2">
                        <input type="text" class="form-control" name="buscaEmp" id="buscaEmp" 
                               placeholder="cedula">
                      </div>
                      <button type="button"  id="buscaBtn" class="btn btn-success">Buscar</button>
                    </div>
                  </form>
                </div>
              </div>
                <div class="container-fluid" id="editEmpl">
                  <!--ACA CARGO LOS EMPLEADOS DE LA BASE DE DATOS-->
                </div>
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
