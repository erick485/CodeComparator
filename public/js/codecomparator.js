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
  
  setTimeout(function(){
  	//alert($("#cursos>option").size());
 var dimen = $("#cursos>option").size();
    
        //divs = jQuery.unique("#cursos>option");
   var vect=[];
  	for( var i=1;i<=dimen;i++){
     	vect.push($("#cursos>option:nth-child("+i+")").attr("id"));
     }
    
    var aux=eliminateDuplicates(vect);
    $("#cursos>option").remove();

    $("#cursos").append("<option>Seleccione curso</option>");

    for(var i=1;i<aux.length;i++){
    	//if (true) {};
         $("#cursos").append("<option>"+aux[i]+"</option>");
    }
     console.log( aux);

  }, 500);

  function eliminateDuplicates(arr) {
 var i,
     len=arr.length,
     out=[],
     obj={};

 for (i=0;i<len;i++) {
    obj[arr[i]]=0;
 }
 for (i in obj) {
    out.push(i);
 }
 return out;
};
  


  $("#cursos").change(function(){
   // alert($("#cursos").val());
   //$("#grup").trigger("change");
    var cadena=String($("#cursos").val());

     $("#grupos").load("http://localhost:9000/codecomparator/alumn?curso="+escape(cadena));
     /*$.ajax({
         url:"hfttp://localhost:9000/codecomparator/alumn?curso="+$("#cursos").val(),
         success:function(){
          alert("ajax");
         }
     });*/
  //$(location).attr('href',"http://localhost:9000/codecomparator/alumn?cur="+$("#cursos").val());
    /*$.get("http://localhost:9000/codecomparator/alumn",{curso:$("#cursos").val()},function(){
      alert("fin de GET")
    })*/
  });
});
