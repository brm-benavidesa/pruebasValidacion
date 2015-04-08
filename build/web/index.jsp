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
    <link rel="stylesheet" type="text/css" href="js/libs/twitter-bootstrap/css/bootstrap.css" media="all" />
    <title>Guardar empleado</title>
    <style type="text/css">
      a{
        text-decoration: none;
        color: black;
      }
    </style>
  </head>
  <body>
    <br />
    <center>
      <h2>Seleccione e ingrese al sistema</h2>
    </center>
    <br />
    <form action="Sistema" class="form-horizontal" method="post" role="form">
      <div class="col-lg-7 control-label">
        <button type="button" class="btn btn-info"><a href="admin.jsp">Administridor</a></button>
        <button type="button" class="btn btn-success"><a href="calculos.html">Empleado</a></button>
      </div>
    </form>
  </body>
</html>
