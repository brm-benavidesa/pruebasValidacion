  //FUNCION QU ENVIA DATOS PARA ACTUALIZAR
  function editar(id) {
    $("#empleadoNum"+id).validate({
      rules: {
        editCel: {required: true, digits: true},
        editNom: {required: true},
        editSue: {required: true, number: true},
        editFec: {required: true}
      }, messages: {
        editCel: {required: "Ingrese la Cedula del Empleado", digits: "Ingrese solo numeros"},
        editNom: {required: "Ingrese el nombre del Empleado"},
        editSue: {required: "Ingrese el sueldo del empleado", number: "Ingrese solo numeros"},
        editFec: {required: "Ingrese la fecha de contratacion"}
      }
    });
    if($("#empleadoNum"+id).valid()){
      var confirma = confirm("Actualizar la informacion del empleado: "+$("#name"+id).val());
      if(confirma){
        $.ajax({
          url: "Sistema",
          type: 'POST',
          //dataType:"json",
          data: $("#empleadoNum" + id).serialize() + "&actualizar=ok"
        }).success(function (data) {
            if(!data.empleados){
              alert("Error al editar empleado");
              traeEmpleados();
            }else{
              alert("Empleado editado con exito");
            }
        });
      }else{
        traeEmpleados();
      }
    }
  };
  //FUNCION QUE ELIMINA EMPLEADO
  function eliminar(id) {
      var confirma = confirm("Eliminar el empleado: "+$("#name"+id).val());
      if(confirma){
        $.ajax({
          url: "Sistema",
          type: 'POST',
          //dataType:"json",
          data: {elimino:"ok",whereElimino:$("#where"+id).val()}
        }).success(function (data) {
          alert("Registro eliminado");
           traeEmpleados(); 
        });
      }else{
        traeEmpleados();
      }
    
  };
  //TRAER TODOS LOS EMPLEADOS
  var traeEmpleados = function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      //dataType:"json",
      data: {traerEmpleados: "ok"}
    }).success(function (data) {
      tableEmpleado(data);
    });
  };
    //ARMO LA TABLA DE EMPLEADOS
  var tableEmpleado = function (data) {
    $("#editEmpl").empty();
    var empleado;
    empleado = "<div class='row'> <div class='col-lg-2 col-md-2 table-bordered'>Cedula</div> <div class='col-lg-2 col-md-2 table-bordered'>Nombre</div> <div class='col-lg-2 col-md-2 table-bordered'>Cargo</div> <div class='col-lg-2 col-md-2 table-bordered'>Salario</div> <div class='col-lg-2 col-md-2 table-bordered'>Fecha de Ingreso</div> <div class='col-lg-1 col-md-1 table-bordered'>Editar</div> <div class='col-lg-1 col-md-1 table-bordered'>Eliminar</div> </div>";
    $.each(data.empleados, function (key, value) {
      empleado += "<form id='empleadoNum" + key + "'>";
      empleado += "<div class='row'>";
      empleado += "<input type='hidden' name='where' id='where" + key + "' value='" + value[0] + "'/>";
      empleado += "<div class='col-lg-2 col-md-2 table-bordered'><input type='text' class='form-control' name='editCel'  value='" + value[0] + "'/></div>";
      empleado += "<div class='col-lg-2 col-md-2 table-bordered'><input type='text' class='form-control' name='editNom' id='name" + key + "' value='" + value[1] + "'/></div>";
      empleado += "<div class='col-lg-2 col-md-2 table-bordered'>" + cargo(value[2], key) + "</div>";
      empleado += "<div class='col-lg-2 col-md-2 table-bordered'><input type='text' class='form-control' name='editSue' value='" + value[3] + "'/></div>";
      empleado += "<div class='col-lg-2 col-md-2 table-bordered'><input type='date' class='form-control' name='editFec' value='" + value[4] + "'/></div>";
      empleado += "<div class='col-lg-1 col-md-1 table-bordered glyphicon glyphicon-pencil editar' onClick='javascript:editar(" + key + ")' ></div>";
      empleado += "<div class='col-lg-1 col-md-1 table-bordered glyphicon glyphicon-minus-sign borrar' onClick='javascript:eliminar(" + key + ")'></div>";
      empleado += "</div>";
      empleado += "</form>";
    });
    $("#editEmpl").append(empleado);
  };
  //FUNCION PARA MOSTRAR EL CARGO DEL EMPLEADO
  var cargo = function (cargo, id) {
    var select = "";
    if (cargo === "A") {
      select += "<select class='form-control' name='tipoCargo' id='tipoCargo" + id + "'>";
      select += "<option value='A' selected='ture'>Administrativo</option>";
      select += "<option value='O'>Operativo</option>";
      select += " <option value='D'>Directivo </option>";
      select += "</select>";
    } else if (cargo === "O") {
      select += "<select class='form-control' name='tipoCargo' id='tipoCargo" + id + "'>";
      select += "<option value='A'>Administrativo</option>";
      select += "<option value='O' selected='ture'>Operativo</option>";
      select += "<option value='D'>Directivo </option>";
      select += "</select>";
    } else if (cargo === "D") {
      select += "<select class='form-control' name='tipoCargo' id='tipoCargo" + id + "'>";
      select += "<option value='A'>Administrativo</option>";
      select += "<option value='O'>Operativo</option>";
      select += "<option value='D' selected='ture'>Directivo </option>";
      select += "</select>";
    }
    return select;
  };

$(document).ready(function () {
  //INGRESAS COMO ADMINISTRADOR AL SISTEMA
  var x = document.cookie;
  if(x!==""){
    $("#logIn").css("display", "none");
    $("#admin").fadeIn();
  }
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
        document.cookie="entro=ok; path=/";
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

  $("#tablaEmpleados").click(traeEmpleados);

  //TRAER LAS VARIABLES DEL SISTEMA
  var variablesSistema = function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      data: {traeVariable: "true"}
    }).success(function (data) {
      $('#valSMMLV').text(data.SMMLV);
      $('#valInteres').text(data.interes);
    });
  };
  variablesSistema();
  //GUARDAR UNA VARIABLE
  $("#guardaVariable").click(function () {
    if($("#guardarVariables").valid()){
      $.ajax({
        url: "Sistema",
        type: 'POST',
        data: $("#guardarVariables").serialize()
      }).success(function (data) {
        if ((data.respuesta === "ok")) {
          $("#varMsg").fadeIn();
          $("#varMsg").fadeOut(5000);
          variablesSistema();
        }
        else {
          $("#varMsg").text(data.respuesta);
          $("#varMsg").fadeIn();
          $("#varMsg").fadeOut(5000);
        }
      });
      }
  });
  //TRAER Y/O BUSCAR UN EMPLEADO
  $("#buscaBtn").click(function () {
    if($("#formEdit").valid()){
      $.ajax({
        url: "Sistema",
        type: 'POST',
        data: $("#formEdit").serialize()
      }).success(function (data) {
        if(data['empleados'][0][4]==="Error"){
          alert("El usuario no esta registrado");
          traeEmpleados();
        }else{
          tableEmpleado(data);
        }
      });
    }
  });
  //TRAER LAS VARIABLES DEL SISTEMA
  var topesXSalario = function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      data: {topesXSalario: "true"}
    }).success(function (data) {
      $('#valDirec').text(data.direc);
      $('#valOpera').text(data.opera);
      $('#valAdmin').text(data.admin);
    });
  };
  topesXSalario();
  //GUARDAR UNA TOPE MAXIMO
  $("#guardaTope").click(function () {
    if($("#guardarTope").valid()){
      $.ajax({
        url: "Sistema",
        type: 'POST',
        data: $("#guardarTope").serialize()
      }).success(function (data) {
        if ((data.respuesta === "ok")) {
          $("#topeMsg").fadeIn();
          $("#topeMsg").fadeOut(5000);
          topesXSalario();
        }
        else {
          $("#topeMsg").text(data.respuesta);
          $("#topeMsg").fadeIn();
          $("#topeMsg").fadeOut(5000);
        }
      });
    }
  });
  //GUARDAR UN EMPLEADO
  $("#guardaEmpleado").click(function () {
    if ($("#guardarEmpleado").valid()) {
      $.ajax({
        url: "Sistema",
        type: 'POST',
        //dataType:"json",
        data: $("#guardarEmpleado").serialize()
      }).success(function (data) {
        if ((data.respuesta === "ok")) {
          $("#empMsg").fadeIn();
          $("#empMsg").fadeOut(5000);
        } else if (data.respuesta === "Existe") {
          alert("El numero de Cédula ya se encuentra registrado en el sistema");
        }
        else {
          alert("Error en los datos de autenticacion");
        }
      });
    }
  });
});


