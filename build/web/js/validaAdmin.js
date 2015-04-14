$(document).ready(function(){
  $("body").click(function(perrito){
    
   
  $("#guardarEmpleado").validate({
     
    rules: {
        juan: {required: true,number: true},
        nombre:{required: true},
        sueldo:{required: true,number: true},
        fechaIngreso:{required: true}
      },messages:{
        id: {required: "Ingrese la Cedula del Empleado",number: "Ingrese solo numeros"},
        nombre:{required: "Ingrese el nombre del Empleado"},
        sueldo:{required: "Ingrese el sueldo del empleado",number: "Ingrese solo numeros"},
        fechaIngreso:{required: "Ingrese la fecha de contratacion"}
      }
  });
  
  })
});
