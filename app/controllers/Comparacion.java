package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.Code.*;
import views.html.*;

public class Comparacion extends Controller {


     public static Result asigAlum(String curso,String grupo,String tit){
        
        Usuario user=Usuario.findByUser(session().get("usuario")); 

     	
     	return ok(asigAlum.render(curso,grupo,tit,Alumno.findAlumno(user.n_docente_id,curso,grupo)));
     }
     public static Result home(){
     	return ok(homeCode.render());
     }
}