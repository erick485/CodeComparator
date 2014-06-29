var x,y;
x=$(document);
x.ready(dragDrop_item_lista);
function dragDrop_item_lista()
{ 
	var x;
	x=$(".itemDragable");
	x.draggable({helper: 'clone'});
	$(".lista").droppable({
	accept: ".itemDragable",                
	drop: function(ev, ui) {

	$(this).append($(ui.draggable));                    
	}
	});
}

x.ready(dradDrop_item_boton);
function dradDrop_item_boton()
{ 

	var y;
	y=$(".itemDragable");
	y.draggable({helper: 'clone'});
	$(".header_botom").droppable({
	accept: ".itemDragable",                

	drop: function(ev, ui) {

		//alert($("#table_list>tr").length);
		var dimen=$("#table_cod>tr").length;

      //   alert("Hola Mundo");
		//$(this).parent().children("span").show();
		//alert($(this).parent().children("span").text());
		$(this).append($(ui.draggable));

     setTimeout(function(){
        //alert(dimen);
	for(var i=1;i<=dimen;i++){
              
           if($("#table_cod>tr:nth-child("+i+") td").find("div").length){
           	
           }
           else{
          // 	alert(i);
           	$("#table_cod>tr:nth-child("+i+") td").find("span").show();
            
            $("#table_cod>tr:nth-child("+i+")").css("background","#fff"); 
          // 	.css("background","#fff");
           }
		}
      }, 300);
	
	}
	
	});

}










