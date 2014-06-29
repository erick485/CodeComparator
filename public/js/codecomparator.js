
$(function(){
  
   $("#save_conf").click(function(){

     
    $("#list_eval").append("<li><a>Asignar Alumno</a></li>");
    //setTimeout(function(){
     $("#asig_alum").show();
     $("#examen").show();
    // alert( $("#titulo").val()+$("#hora").val()+$("#min").val()+$("#seg").val());

   // },500);   
    titulo=$("#titulo").val();
    tiempo=$("#hora").val()+":"+$("#min").val()+":"+$("#seg").val();
    curso=$("#cursos").val();
    grupo=$("#grup").val();
    descrip=$("#descripcion").val();

        $(this).attr("href","http://localhost:9000/codecomparator/asigAlum?tit="+escape(titulo)+"&tiempo="+escape(tiempo)+"&curso="+escape(curso)+"&grupo="+escape(grupo)+"&descr="+escape(descrip));
   });

  for(var i=0;i<6;i++){
    $("#hora").append("<option>0"+i+"</option>");
  }
  for(var i=0;i<60;i++){
    if(i<10)
     i="0"+i; 
    $("#min").append("<option>"+i+"</option>");
    $("#seg").append("<option>"+i+"</option>")
  }

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

    hor=$("#time_span").text().substring(0,2);
    min=$("#time_span").text().substring(3,5);
    seg=$("#time_span").text().substring(6,8);
    
 if(hor.substring(0,1)=="0"){
     hor=hor.substring(1,2);
     //alert(hor);
 }
 if(min.substring(0,1)=="0"){
   min=min.substring(1,2);
      //alert(min);

 } 
 if(seg.substring(0,1)=="0"){
    seg=seg.substring(1,2);
    //alert(seg);
 }
    //alert(hor.substring(0,1)+"/"+min.substring(0,1)+"/"+seg.substring(0,1));

   // alert(hor+"/"+min+"/"+seg);
      $("#inicio").hide();
      $("#final").show();
      $("#time").chrony({
         hour:hor,
         minute:min,
         second:seg,
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

     $("#div_grupo").load("http://localhost:9000/codecomparator/confExam?curso="+escape(cadena));

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
