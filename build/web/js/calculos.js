$( document ).ready(function() {
  
  //BUSCAR UN EMPLEADO POR CEDULA
  $("#calculo").validate({
    rules:{
      consultaEmpleado: "required"
    }
  });
  $("#calculoPuntos").validate({
    rules:{
      estadoCivil: "required",
      numHijos: "required",
      casaPropia: "required",
      estrato: "required"
    },messages:{
      estadoCivil: "Campo requerido",
      numHijos: "Campo requerido",
      casaPropia: "Campo requerido",
      estrato: "Campo requerido"
    }
  });
  $("#enviar").click(function(){   
    if($("#calculo").valid()){
      $.ajax({
        url: "Sistema",
        type: 'POST',
        //dataType:"json",
        data:$("#calculo").serialize()
      }).success(function(data) {
          if((data.respuesta==="Error")){
            alert("El usuario no esta registrado");
        }else{
          $("#datos").css('display','block');
          puntosTopeMax();
        }
      });
      
    }
  });
  //CALCULAR LOS PUNTOS DEL EMPLEADO QUE ESTOY CONSULTANDO
  var puntosTopeMax = function(){
    if($("#calculoPuntos").valid()){
      $.ajax({
        url: "Sistema",
        type: 'POST',
        data:$("#calculoPuntos").serialize()+"&consultaEmpleado="+$("#consultaEmpleado").val()
      }).success(function(data) {
        $("#valorMaxPrestamo").val(data.valorTotal);
        $("#totalPuntos").text(data.puntos);
        $("#totalPres").text(data.valorTotal);
        $("#valNombre").text(data.nombre);
        $("#valCargo").text(data.cargo);
        $("#valSueldo").text(data.sueldo);
        $("#valFecha").text(data.fecha);
        $("#prestamo").css("display","block");
      });
    }
  };
  $(".reCalc").change(puntosTopeMax);
  //CALCULAR EL VALOR DE LAS CUOTAS DEL EMPLEADO QUE ESTOY CONSULTANDO
  $("#calcuCuotas").click(function(){
    if($("#prestamo").valid()){
      $.ajax({
        url: "Sistema",
        type: 'POST',
        data:$("#prestamo").serialize()+"&consultaEmpleado="+$("#consultaEmpleado").val(),
      }).success(function(data) {
  //       $("#mensaje").text("Usted tiene un total de "+data.puntos+" puntos y puede hacer un prestamo maximo de: "+data.valorTotal);
  //       $("#prestamo").css("display","block");
        if(data.totalCuotas==-2){
          alert("No puede solicitar más del su maximo permitido");
        }else if(data.totalCuotas==-1){
          alert("Error en los datos");
        }else{
          alert("Sus cuotas mensuales serian de "+(data.totalCuotas).toFixed(2)+" Pesos");
        }
      });
    }
  });
    
});