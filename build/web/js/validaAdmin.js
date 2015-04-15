$(document).ready(function(){
  //FORMULARIO PARA GUARDAR EMPLEADO
  $("#guardarEmpleado").validate({
    rules: {
        id: {required: true,digits: true},
        nombre:{required: true},
        sueldo:{required: true,number: true},
        fechaIngreso:{required: true}
      },messages:{
        id: {required: "Ingrese la Cedula del Empleado",digits: "Ingrese solo numeros"},
        nombre:{required: "Ingrese el nombre del Empleado"},
        sueldo:{required: "Ingrese el sueldo del empleado",number: "Ingrese solo numeros"},
        fechaIngreso:{required: "Ingrese la fecha de contratacion"}
      }
  });
  //BUSCAR EMPLEADO PARA EDITAR
  $("#formEdit").validate({
    rules: {
        buscaEmp: {required: true,digits: true}
      },messages:{
        buscaEmp: {required: "Ingrese la Cedula del Empleado",digits: "Ingrese solo numeros"}
      }
  });
  //GUARDAR VARIABLES
  $("#guardarVariables").validate({
    rules: {
        SMMLV: {number: true},
        interes: {number: true,max: 1}
      },messages:{
        SMMLV: {digits: "Ingrese solo numeros"},
        interes: {digits: "Ingrese solo numeros",max:"La taza de interes no puede superar 1 que equivale al 100%"}
      }
  });
  //GUARDAR TOPES
  $("#guardarTope").validate({
    rules: {
        admin: {digits: true},
        opera: {digits: true},
        direc: {digits: true}
      },messages:{
        admin: {digits: "Ingrese solo numeros"},
        opera: {digits: "Ingrese solo numeros"},
        direc: {digits: "Ingrese solo numeros"}
      }
  });
});
