$(function(){
 
 var aux=0;
  $(".pep").pep();
  $("#table_cod>tr .pep").hover(function(){
       $(this).parent().parent().css("background","#f7f7f9");
  },function(){
  	$(this).parent().parent().css("background","#fff");
  });
 });