$(function(){
 
 var  num_comp = [];
var  cod_alum=[];

  $("#table_cursos").dataTable();
  $('#table_cursos_wrapper').append("<div id='footer-table'></div>");
  $('#table_cursos_next').html("<i class='glyphicon glyphicon-forward'></i>");
  $('#table_cursos_previous').html("<i class='glyphicon glyphicon-backward'></i>");

  $('.dataTables_filter input').attr('placeholder', 'Buscar..');

   $("#asig_alum").click(function(){
          $("#asignar_alum").show();

          $("#examen_time").hide();
   });

   $("#examen").click(function(){
      $("#asignar_alum").hide();
      $("#examen_time").show();

      $("#clock").load("http://localhost:9000/codecomparator/time");

      var dimen=$("#table_cod>tr").length;
      //alert(dimen);
   var elem=$("#cont_lab >div"); 

  /* alert($("#cont_lab >div:eq(0)> div:eq(0) span").text());
     alert($("#cont_lab >div:eq(1)").html());

     alert($("#cont_lab >div:eq(2)").html());
*/


      for(var i=0;i<4;i++){

      	for(var j=0;j<4;j++){
          var aux=$("#cont_lab >div:eq("+i+") >div:eq("+j+") span").attr("data");

        
        if($("#cont_lab >div:eq("+i+") >div:eq("+j+") span div").length){
          
          var auxi=$("#cont_lab >div:eq("+i+") >div:eq("+j+") span div").text();

          cod_alum.push(auxi);
         // console.log(auxi);
        }
        else{
             cod_alum.push(0);
        }

          var aux=$("#cont_lab >div:eq("+i+") >div:eq("+j+") span div").text();

           num_comp.push(aux);
            
            //console.log(aux);
      	}
      } 

      for(var y=0;y<cod_alum.length;y++){
           if(cod_alum[y]=="0"){

           }
           else{
           	for(var d=0;d<$("#exam_alumno>tr").length;d++){
               
               if($("#exam_alumno>tr:eq("+d+") td:eq(0)").text()==cod_alum[y]){
                 
                 $("#exam_alumno>tr:eq("+d+")").show();
               }
           	} 
            /*if($("#exam_alumno>tr").length){

            }
           console.log($("#exam_alumno>tr:eq(0) td:eq(0)").text());
           	console.log("ALUMNOOOO!!!!"+cod_alum[y]);
*/

           }
         }



   });

  
    $("#mod_alum").click(function(){
       // alert("Hola Mundo");

        //$(this).attr("href","http://localhost:9000/CodeAlumno/envio?time=24");
            // alert($("#time").text());
        window.open("http://localhost:9000/CodeAlumno/envio?time="+$("#time").text()+"&band=true",'_blank');
    });
    // $.get( "http://localhost:9000/CodeAlumno/envio?time=24" );


 });