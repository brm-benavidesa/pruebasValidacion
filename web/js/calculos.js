$( document ).ready(function() {
   //INGRESAS COMO ADMINISTRADOR AL SISTEMA
  $("#logButton").click(function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      //dataType:"json",
      data:$("#logIn").serialize()
    }).success(function(data) {
				if((data.respuesta==="ok")){
          $("#logIn").css("display","none");
          $("#admin").fadeIn();
        }
        else{
          alert("Error en los datos de autenticacion")
        }
		});
    
  });
  //GUARDAR UN EMPLEADO
  $("#guardaEmpleado").click(function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      //dataType:"json",
      data:$("#guardarEmpleado").serialize()
    }).success(function(data) {
				if((data.respuesta==="ok")){
          $("#empMsg").fadeIn();
          $("#empMsg").fadeOut(5000);
        }
        else{
          alert("Error en los datos de autenticacion");
        }
		});
    
  });
  //GUARDAR UNA VARIABLE
  $("#guardaVariable").click(function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      data:$("#guardarVariables").serialize()
    }).success(function(data) {
				if((data.respuesta==="ok")){
         $("#varMsg").fadeIn();
         $("#varMsg").fadeOut(5000);
        }
        else{
          $("#varMsg").text(data.respuesta);
          $("#varMsg").fadeIn();
          $("#varMsg").fadeOut(5000);
        }
		});
    
  });
  //BUSCAR UN EMPLEADO POR CEDULA
  $("#enviar").click(function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      //dataType:"json",
      data:$("#calculo").serialize()
    }).success(function(data) {
				if((data.respuesta!=="noEncontrado")){
          $("#datos").css('display','block');
        }
		});
    
  });
  //CALCULAR LOS PUNTOS DEL EMPLEADO QUE ESTOY CONSULTANDO
  $("#calcuPuntos").click(function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      //dataType:"json",
      data:$("#datos").serialize()+"&consultaEmpleado="+$("#consultaEmpleado").val(),
    }).success(function(data) {
        $("#valorMaxPrestamo").val(data.valorTotal);
       $("#mensaje").text("Usted tiene un total de "+data.puntos+" puntos y puede hacer un prestamo maximo de: "+data.valorTotal);
       $("#prestamo").css("display","block");
		});
  });
  //CALCULAR EL VALOR DE LAS CUOTAS DEL EMPLEADO QUE ESTOY CONSULTANDO
  $("#calcuCuotas").click(function(){
    $.ajax({
      url: "Sistema",
      type: 'POST',
      data:$("#prestamo").serialize()+"&consultaEmpleado="+$("#consultaEmpleado").val(),
    }).success(function(data) {
//       $("#mensaje").text("Usted tiene un total de "+data.puntos+" puntos y puede hacer un prestamo maximo de: "+data.valorTotal);
//       $("#prestamo").css("display","block");
        alert(data.totalCuotas);
		});
  });
    
});