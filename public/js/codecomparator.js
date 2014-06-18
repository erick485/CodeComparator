$(function(){
  
  $('#login').validate({
      
      rules:{
      	t_usuario:{
         required:true
      	},
      	t_password:{
      		required:true
      	}
      },
      messages:{
      	t_usuario:{
           required:"Debe ingresar su usuario"
      	},
      	t_password:{
           required:"Debe ingresar su contraseña"
      	}
      },
      highlight: function(element) {
        $(element).closest('.form-group').addClass('has-error');
      },
      unhighlight: function(element) {
        $(element).closest('.form-group').removeClass('has-error');
      },
       errorElement: 'span',
    errorClass: 'help-block',
    errorPlacement: function(error, element) {
        if(element.parent('.input-group').length) {
            error.insertAfter(element.parent());
          $("#message_error").hide();
        } else {
            error.insertAfter(element);
          $("#message_error").hide();
        }
    }

  });

  $('#crearDocent').validate({
   rules:{
        t_usuario:{
         required:true
        },
        t_apellido_paterno:{
         required:true
        },
        t_nombre:{
         required:true
        },
        t_password:{
          required:true
        },
        t_codigo:{
          required:true,
          digits:true
        },
        rep_password:{
          required:true
        },
        t_apellido_materno:{
          required:true
        }
      },
       
       highlight: function(element) {
        $(element).closest('.form-group').addClass('has-error');
      },
      unhighlight: function(element) {
        $(element).closest('.form-group').removeClass('has-error');
      },
       errorElement: 'span',
       errorClass: 'help-block',
       errorPlacement: function(error, element) {
        if(element.parent('.input-group').length) {
            error.insertAfter(element.parent());
            $("#message_clave").hide();
            $("#message_usuario").hide();
            $("#message_cod").hide();
        } else {
          $("#message_cod").hide();
          $("#message_clave").hide();
          $("#message_usuario").hide();
            error.insertAfter(element);
        
        }
    }
  });

  $("#inicio").click(function(){
      $("#inicio").hide();
      $("#final").show();
      $("#time").chrony({
         hour:0,
         minute:1,
         second:3,
         finish:function(){

         }
      });
  });

  $("#final").click(function(){
        $("#time").addClass("color_time");
        $("#time").text("00:00:00");
  });
});