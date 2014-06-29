$(function(){
 
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
var  num_comp = [];
var  cod_alum=[];

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


 });