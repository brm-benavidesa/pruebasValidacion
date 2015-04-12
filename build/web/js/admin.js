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


