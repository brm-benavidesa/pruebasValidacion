$(document).ready(function () {
  //INGRESAS COMO ADMINISTRADOR AL SISTEMA
  $("#logButton").click(function () {
    $.ajax({
      url: "Sistema",
      type: 'POST',
      //dataType:"json",
      data: $("#logIn").serialize()
    }).success(function (data) {
      if ((data.respuesta === "ok")) {
        $("#logIn").css("display", "none");
        $("#admin").fadeIn();
      }
      else {
        alert("Error en los datos de autenticacion")
      }
    });
  });
  //TRAER LAS VARIABLES ACTUALES DEL SISTEMAS
    $("#logButton").click(function () {
    $.ajax({
      url: "Sistema",
      type: 'POST',
      //dataType:"json",
      data: $("#logIn").serialize()
    }).success(function (data) {
      if ((data.respuesta === "ok")) {
        $("#logIn").css("display", "none");
        $("#admin").fadeIn();
      }
      else {
        alert("Error en los datos de autenticacion")
      }
    });
  });
  //TRAER TODOS LOS EMPLEADOS
  $.ajax({
    url: "Sistema",
    type: 'POST',
    //dataType:"json",
    data: {traerEmpleados:"ok"}
  }).success(function (data) {
    var empleado;
    empleado = "";
    $.each(data.empleados,function( key, value ) {
      empleado +="<tr>"; 
      empleado +="<td><input type='text' id='editCel"+key+"' value='"+value[0]+"'/></td>"; 
      empleado +="<td><input type='text' id='editNom"+key+"' value='"+value[1]+"'/></td>"; 
      empleado +="<td><input type='text' id='editCar"+key+"' value='"+value[2]+"'/></td>"; 
      empleado +="<td><input type='text' id='editSue"+key+"' value='"+value[3]+"'/></td>"; 
      empleado +="<td><input type='text' id='editFec"+key+"' value='"+value[4]+"'/></td>"; 
      empleado +="<td><span class='glyphicon glyphicon-pencil editar'></span></td>"; 
      empleado +="<td><span class='glyphicon glyphicon-minus-sign borrar'></span></td>"; 
      empleado +="</tr>"; 
    });
      $("#editEmpl").append(empleado);
  });
  //GUARDAR UN EMPLEADO
  $.ajax({
    url: "Sistema",
    type: 'POST',
    data: {traeVariable:"true"}
  }).success(function (data) {
    $('#valSMMLV').text(data.SMMLV);
    $('#valInteres').text(data.interes);
  });
  //GUARDAR UNA VARIABLE
  $("#guardaVariable").click(function () {
    $.ajax({
      url: "Sistema",
      type: 'POST',
      data: $("#guardarVariables").serialize()
    }).success(function (data) {
      if ((data.respuesta === "ok")) {
        $("#varMsg").fadeIn();
        $("#varMsg").fadeOut(5000);
      }
      else {
        $("#varMsg").text(data.respuesta);
        $("#varMsg").fadeIn();
        $("#varMsg").fadeOut(5000);
      }
    });

  });
  //GUARDAR UNA TOPE MAXIMO
  $("#guardaTope").click(function () {
    $.ajax({
      url: "Sistema",
      type: 'POST',
      data: $("#guardarTope").serialize()
    }).success(function (data) {
      
    });

  });
});


