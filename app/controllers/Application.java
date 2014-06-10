package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.Templates.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return redirect("/codecomparator");
    }
    public static Result codecomparator(){


         return ok(logging.render());
    }
    public static Result crearCuenta(){
        return ok(crearCuenta.render());
    }
     public static Result curso(){
        return ok(registrarGrupo.render());
    }
     public static Result alumno(){
        return ok(registrarAlum.render());
    }
    
    public static Result historiales(){
        return ok(historiales.render());
    } 

     public static Result confEvaluacion(){
        return ok(confEval.render());
    }
    public static Result asigAlum(){
        return ok(asignarAlum.render());
    }
    public static Result iniciarExam(){
        return ok(IniciarExamen.render());
    }
    public static Result compProy(){
        return ok(compProy.render());
    }
    public static Result cambContr(){
        return ok(updateClave.render());
    }
    public static Result authenticate() {

         System.out.print("Hola Mundo");
        
        Form<Usuario> docentForm = form(Usuario.class).bindFromRequest();

        if (docentForm.hasErrors()) {
            return badRequest();
        } else {
            
            if(Usuario.authenticate(docentForm.get().t_usuario,docentForm.get().t_password)==null){
                flash("success","El usuario o la contraseña incorretos");
                return badRequest(logging.render());
             }   
            else{
                session().clear();
                session("usuario", docentForm.get().t_usuario);
                return redirect(routes.Application.home());
            }
        }
    
     }
     public static Result logout() {
        session().clear();
        
        return redirect(
            routes.Application.index()
        );
     }
     public static Result addCuenta() {
        Form<Usuario> docentForm = form(Usuario.class).bindFromRequest();
        Form<Docente> docente=form(Docente.class).bindFromRequest(); 
        if(docentForm.hasErrors()){
             return badRequest();
        } 
        else{
            String aux=docentForm.get().t_password;
            String aux1=docentForm.get().rep_password;

            if(aux.equals(aux1)){
                
            if(Docente.findByCod(docente.get().t_codigo) != null ){
               flash("codi","El codigo");
               return badRequest(crearCuenta.render());
            } else{
                     
            if(Usuario.findByEmail(docentForm.get().t_usuario)==null){
                Docente.create( new Docente(),docente.get().t_nombre,docente.get().t_codigo,docente.get().t_apellido_paterno,docente.get().t_apellido_materno);                
                
                Docente docent=Docente.findByCod(docente.get().t_codigo); 
                 
                Usuario.create(docent.n_docente_id,docentForm.get().t_usuario,docentForm.get().t_password,docentForm.get().rep_password);
                flash("registro","Se registró correctamente");
                return badRequest(crearCuenta.render());            
             }
            else{
                 
                flash("usua","Usuario ya existe"); 
               return badRequest(crearCuenta.render());
               
               }                 
            }    
                             
            } 
            else{
                
               
                 flash("claves","El campo confirmar contraseña y contraseña deben coincidir");
                 return badRequest(crearCuenta.render());                
           
        }
        }


     }

     public static Result home(){
         return ok(home.render());
     }

}
