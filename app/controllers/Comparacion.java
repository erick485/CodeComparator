package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.Code.*;
import views.html.*;

public class Comparacion extends Controller {


     public static Result asigAlum(String curso,String grupo,String tit,String tiempo){
        
        Usuario user=Usuario.findByUser(session().get("usuario")); 
          
          session("curso",curso);
          session("titulo",tit);

     	
     	return ok(asigAlum.render(curso,grupo,tit,tiempo,Alumno.findAlumno(user.n_docente_id,curso,grupo)));
     }
     public static Result home(){
     	return ok(homeCode.render());
     }

     public static 	Result envio(String time,String band){
        Usuario user=Usuario.findByUser(session().get("usuario")); 

        Docente doc=Docente.find.byId(user.n_docente_id);
        String curso=session().get("curso");  
        String tit=session().get("titulo");
     	return ok(EnviarProyecto.render(curso,tit,doc,time,band));
     }

     public static Result time(){
     	return ok(timeCode.render());
     }
}